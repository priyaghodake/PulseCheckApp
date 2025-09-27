package com.example.pulsecheck.notification.strategies.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component()
public class SmtpEmailStrategy implements EmailStrategy {
    private final JavaMailSender mailSender;

    @Autowired
    public SmtpEmailStrategy(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("alerts@pulsecheck.dev");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
