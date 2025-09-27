package com.example.pulsecheck.notification.strategies.email;

public interface EmailStrategy {
    void send(String to, String subject, String body);
}

