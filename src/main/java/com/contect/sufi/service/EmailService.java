package com.contect.sufi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // 1. Send message to YOU (admin), reply-to set to user's email
    public void sendContactEmail(String to, String subject, String text, String replyTo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("sufi111729@gmail.com");  // Your Gmail
            helper.setTo(to);                        // You (admin)
            helper.setSubject(subject);
            helper.setText(text);
            helper.setReplyTo(replyTo);              // Reply goes to user

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("❌ Failed to send contact email: " + e.getMessage());
        }
    }

    // 2. Auto-reply to user
    public void sendAutoReply(String userEmail, String subject, String text) {
        try {
            MimeMessage reply = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(reply, true);

            helper.setFrom("sufi111729@gmail.com");  // Your Gmail
            helper.setTo(userEmail);                 // User
            helper.setSubject(subject);
            helper.setText(text);

            mailSender.send(reply);
        } catch (MessagingException e) {
            throw new RuntimeException("❌ Failed to send auto-reply: " + e.getMessage());
        }
    }
}
