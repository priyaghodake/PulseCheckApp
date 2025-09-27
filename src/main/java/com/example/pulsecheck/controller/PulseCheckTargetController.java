package com.example.pulsecheck.controller;

import com.example.pulsecheck.model.PulseCheckTarget;
import com.example.pulsecheck.service.PulseCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pulsecheck/target")
public class PulseCheckTargetController {

    private final PulseCheckService pulseCheckService;

    @Autowired
    public PulseCheckTargetController(PulseCheckService pulseCheckService) {
        this.pulseCheckService = pulseCheckService;
    }

    @PostMapping
    public PulseCheckTarget createTarget(@RequestBody PulseCheckTarget pulseCheckTarget) {
        return pulseCheckService.createTarget(pulseCheckTarget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarget(@PathVariable Long id) {
        pulseCheckService.deleteTarget(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{name}")
    public List<PulseCheckTarget> getTargetsByUser(@PathVariable String name) {
        return pulseCheckService.getTargetsByUser(name);
    }

    @GetMapping("/id/{id}")
    public PulseCheckTarget getTargetById(@PathVariable Long id) {
        return pulseCheckService.getTargetById(id);
    }

    @GetMapping
    public List<PulseCheckTarget> getAllTargets() {
        return pulseCheckService.getAllTargets();
    }

    @PutMapping("/{id}")
    public PulseCheckTarget updateTarget(@RequestBody PulseCheckTarget target, @PathVariable Long id) {
        return pulseCheckService.updateTarget(id, target);
    }
}
