package com.example.pulsecheck.service;

import com.example.pulsecheck.enums.Status;
import com.example.pulsecheck.model.PulseCheckLog;
import com.example.pulsecheck.model.PulseCheckTarget;
import com.example.pulsecheck.repository.PulseCheckLogDAO;
import com.example.pulsecheck.repository.PulseCheckTargetDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PulseCheckService {
    private final PulseCheckTargetDAO pulseCheckTargetDAO;
    private final PulseCheckLogDAO pulseCheckLogDAO;
    private final NotificationService notificationService;

    @Autowired
    public PulseCheckService(PulseCheckTargetDAO pulseCheckTargetDAO, PulseCheckLogDAO pulseCheckLogDAO, NotificationService notificationService) {
        this.pulseCheckTargetDAO = pulseCheckTargetDAO;
        this.pulseCheckLogDAO = pulseCheckLogDAO;
        this.notificationService = notificationService;
    }

    public List<PulseCheckTarget> getAllTargets() {
        return pulseCheckTargetDAO.findAll();
    }

    public List<PulseCheckTarget> getTargetsByUser(String name) {
        return pulseCheckTargetDAO.findByName(name);
    }

    public PulseCheckTarget getTargetById(Long id) {
        return pulseCheckTargetDAO.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Target not found"));
    }

    public PulseCheckTarget createTarget(PulseCheckTarget pulseCheckTarget) {
        return pulseCheckTargetDAO.save(pulseCheckTarget);
    }

    @Transactional
    public void deleteTarget(Long id) {
        PulseCheckTarget target = pulseCheckTargetDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Target not found"));
        pulseCheckLogDAO.deleteByTarget(target);
        pulseCheckTargetDAO.deleteById(id);
    }

    @Scheduled(fixedRate = 60000)
    public void checkAllTargets(){
        List<PulseCheckTarget> targets = pulseCheckTargetDAO.findAll();

        for(PulseCheckTarget target: targets){
            checkTarget(target);
        }
    }

    public void checkTarget(PulseCheckTarget pulseCheckTarget){
        PulseCheckLog pulseCheckLog = new PulseCheckLog();
        pulseCheckLog.setTarget(pulseCheckTarget);
        pulseCheckLog.setCheckedAt(LocalDateTime.now());

        long start = System.currentTimeMillis();

        try{
            RestTemplate restTemplate  = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Pulse-Check-App");
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    pulseCheckTarget.getUrl(),
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );

            long respTime = System.currentTimeMillis() - start;

            pulseCheckLog.setStatus(Status.UP);
            pulseCheckLog.setResponseTime(respTime);

            System.out.println("✅ " + pulseCheckTarget.getUrl() + " responded with " + response.getStatusCode());
        }
        catch(Exception e){
            pulseCheckLog.setStatus(Status.DOWN);
            pulseCheckLog.setResponseTime(-1L);

            System.out.println("❌ Failed to reach " + pulseCheckTarget.getUrl() + " — " + e.getMessage());

//            notificationService.sendFailureAlert(pulseCheckTarget.getEmail(), pulseCheckTarget.getUrl());
        }
        pulseCheckLogDAO.save(pulseCheckLog);
    }

    public PulseCheckTarget updateTarget(Long id, PulseCheckTarget target) {
        PulseCheckTarget updatedTarget = getTargetById(id);
        updatedTarget.setName(target.getName());
        updatedTarget.setEmail(target.getEmail());
        updatedTarget.setUrl(target.getUrl());
        updatedTarget.setFreqInMins(target.getFreqInMins());
        return pulseCheckTargetDAO.save(updatedTarget);
    }
}
