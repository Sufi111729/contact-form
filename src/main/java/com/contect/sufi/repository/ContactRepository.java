package com.contect.sufi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.contect.sufi.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
