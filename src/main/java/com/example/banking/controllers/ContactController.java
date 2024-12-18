package com.example.banking.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.DTO.AddressDto;
import com.example.banking.DTO.ContactDto;
import com.example.banking.services.ContactService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("contacts")
@RequiredArgsConstructor
public class ContactController {
     private final ContactService contactService;
    
    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ContactDto contactDto) {
       
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(contactService.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<Set<ContactDto>> findAll() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactService.findById(id));
    }

    @GetMapping("/find-all-contact-users/{id}")
    public ResponseEntity<Set<ContactDto>> findAllByUserId(@PathVariable("id") Integer id){
         return ResponseEntity.ok(contactService.findAllByUserId(id));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        contactService.delete(id);
         return ResponseEntity.accepted().build();
    }


}
