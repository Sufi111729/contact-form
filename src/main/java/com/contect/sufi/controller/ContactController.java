package com.contect.sufi.controller;

import com.contect.sufi.dto.ContactMessage;
import com.contect.sufi.entity.Contact;
import com.contect.sufi.repository.ContactRepository;
import com.contect.sufi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*") // Allow all origins (adjust in production)
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody ContactMessage msg) {
        // 1. Save to DB
        Contact contact = new Contact();
        contact.setName(msg.getName());
        contact.setEmail(msg.getEmail());
        contact.setSubject(msg.getSubject());
        contact.setMessage(msg.getMessage());
        contactRepository.save(contact);

        // 2. Send Email to Admin
        String adminEmailText = """
                Name: %s
                Email: %s
                Subject: %s
                Message: %s
                """.formatted(msg.getName(), msg.getEmail(), msg.getSubject(), msg.getMessage());

        emailService.sendContactEmail(
                "sufi111729@gmail.com",         // Admin receiver
                "New Contact Message",          // Subject
                adminEmailText,                 // Message body
                msg.getEmail()                  // Reply-To set to user
        );

        // 3. Send Auto-Reply to User
        String autoReplyText = """
                Hi %s,

                Thank you for contacting me! I’ve received your message regarding "%s" and will get back to you shortly.

                Regards,
                Muhammad Sufiyan
                """.formatted(msg.getName(), msg.getSubject());

        emailService.sendAutoReply(
                msg.getEmail(),                 // User email
                "We received your message",     // Subject
                autoReplyText                   // Body
        );

        return ResponseEntity.ok("✅ Message received. Emails sent.");
    }
}
