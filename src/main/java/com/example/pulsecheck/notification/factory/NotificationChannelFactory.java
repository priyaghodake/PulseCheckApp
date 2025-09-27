package com.example.pulsecheck.notification.factory;

import com.example.pulsecheck.notification.NotificationChannel;
import com.example.pulsecheck.notification.channel.EmailNotificationChannel;
import com.example.pulsecheck.notification.channel.MessagingNotificationChannel;
import com.example.pulsecheck.notification.strategies.email.SendGridEmailStrategy;
import com.example.pulsecheck.notification.strategies.email.SmtpEmailStrategy;
import com.example.pulsecheck.notification.strategies.messaging.TwilioSmsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationChannelFactory {

    private final SmtpEmailStrategy smtpEmailStrategy;
    private final SendGridEmailStrategy sendGridEmailStrategy;
    private final TwilioSmsStrategy twilioSmsStrategy;

    @Autowired
    public NotificationChannelFactory(SmtpEmailStrategy smtp, SendGridEmailStrategy sendGrid, TwilioSmsStrategy twilio) {
        this.smtpEmailStrategy = smtp;
        this.sendGridEmailStrategy = sendGrid;
        this.twilioSmsStrategy = twilio;
    }

    public NotificationChannel getChannel(String type, String strategy) {
        if (type.equals("email")) {
            if (strategy.equals("smtp")) {
                return new EmailNotificationChannel(smtpEmailStrategy);
            } else if (strategy.equals("sendgrid")) {
                return new EmailNotificationChannel(sendGridEmailStrategy);
            }
        } else if (type.equals("message")) {
            if (strategy.equals("twilio")) {
                return new MessagingNotificationChannel(twilioSmsStrategy);
            }
        }
        throw new IllegalArgumentException("Unsupported channel/strategy");
    }
}

