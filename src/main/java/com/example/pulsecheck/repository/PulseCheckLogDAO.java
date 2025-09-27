package com.example.pulsecheck.repository;

import com.example.pulsecheck.model.PulseCheckLog;
import com.example.pulsecheck.model.PulseCheckTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PulseCheckLogDAO extends JpaRepository<PulseCheckLog, Long> {
    void deleteByTarget(PulseCheckTarget target);
}
