package com.example.pulsecheck.notification.channel;

import com.example.pulsecheck.notification.NotificationChannel;
import com.example.pulsecheck.notification.strategies.messaging.MessagingStrategy;

public class MessagingNotificationChannel implements NotificationChannel {
    private final MessagingStrategy messagingStrategy;

    public MessagingNotificationChannel(MessagingStrategy messagingStrategy) {
        this.messagingStrategy = messagingStrategy;
    }

    @Override
    public void sendNotification(String to, String message) {
        messagingStrategy.sendMessage(to, message);
    }
}

