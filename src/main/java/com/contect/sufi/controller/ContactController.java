package com.contect.sufi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contect.sufi.dto.ContactMessage;
import com.contect.sufi.entity.Contact;
import com.contect.sufi.repository.ContactRepository;
import com.contect.sufi.service.EmailService;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
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

        // 2. Send Email
        String emailText = """
                Name: %s
                Email: %s
                Subject: %s
                Message: %s
                """.formatted(msg.getName(), msg.getEmail(), msg.getSubject(), msg.getMessage());

        emailService.sendContactEmail("sufi111729@gmail.com", "New Contact Message", emailText);

        return ResponseEntity.ok("Message saved and email sent.");
    }
}