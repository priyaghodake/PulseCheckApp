package com.example.pulsecheck.notification.channel;

import com.example.pulsecheck.notification.NotificationChannel;
import com.example.pulsecheck.notification.strategies.email.EmailStrategy;

public class EmailNotificationChannel implements NotificationChannel {
    private final EmailStrategy emailStrategy;

    public EmailNotificationChannel(EmailStrategy emailStrategy) {
        this.emailStrategy = emailStrategy;
    }

    @Override
    public void sendNotification(String to, String message) {
        emailStrategy.send(to, "PulseCheck Alert", message);
    }
}
