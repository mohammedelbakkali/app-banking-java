package com.example.banking.Repositories;

import com.example.banking.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

    Set<Contact> findAllByUserId(Integer id);
}
