package com.example.pulsecheck.notification.strategies.messaging;

public interface MessagingStrategy {
    void sendMessage(String to, String message);
}
