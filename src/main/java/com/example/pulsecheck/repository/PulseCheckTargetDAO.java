package com.example.pulsecheck.repository;

import com.example.pulsecheck.model.PulseCheckTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PulseCheckTargetDAO extends JpaRepository<PulseCheckTarget, Long> {
    public List<PulseCheckTarget> findByName(String name);
}
