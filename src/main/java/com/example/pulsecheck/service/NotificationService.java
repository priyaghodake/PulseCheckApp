package com.example.pulsecheck.service;

import com.example.pulsecheck.notification.factory.NotificationChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private NotificationChannelFactory notificationChannelFactory;

    @Autowired
    public NotificationService(NotificationChannelFactory notificationChannelFactory) {
        this.notificationChannelFactory = notificationChannelFactory;
    }

    public void sendFailureAlert(String notificationChannelConfig, String to, String apiUrl) {

    }
}
