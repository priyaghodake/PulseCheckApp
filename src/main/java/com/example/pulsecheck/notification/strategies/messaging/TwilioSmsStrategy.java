package com.example.pulsecheck.notification.strategies.messaging;

import org.springframework.stereotype.Component;

@Component
public class TwilioSmsStrategy implements MessagingStrategy {
    public void sendMessage(String to, String message) {
    }
}
