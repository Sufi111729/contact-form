package com.contect.sufi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String subject;
    private String message;

    // Setters and getters...
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getSubject() { return subject; }
    public void setMessage(String message) { this.message = message; }
    public String getMessage() { return message; }
}
