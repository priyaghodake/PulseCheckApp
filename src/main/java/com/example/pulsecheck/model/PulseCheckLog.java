package com.example.pulsecheck.model;

import com.example.pulsecheck.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PulseCheckLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "target_id", referencedColumnName = "id", nullable = true)
    private PulseCheckTarget target;

    private Status status;
    private LocalDateTime checkedAt;
    private Long responseTime;

    public PulseCheckLog() {
    }

    public PulseCheckLog(Long id, PulseCheckTarget target, Status status, LocalDateTime checkedAt, Long responseTime) {
        this.id = id;
        this.target = target;
        this.status = status;
        this.checkedAt = checkedAt;
        this.responseTime = responseTime;
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public PulseCheckTarget getTarget() {
        return target;
    }

    public void setTarget(PulseCheckTarget target) {
        this.target = target;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
