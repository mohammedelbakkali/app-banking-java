package com.example.banking.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.services.AddressService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.banking.DTO.AddressDto;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("address")
@RequiredArgsConstructor

public class AddressController {
    private final AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto) {
       
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(addressService.save(addressDto));
    }

    @GetMapping("/")
    public ResponseEntity<Set<AddressDto>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
         addressService.delete(id);
         return ResponseEntity.accepted().build();
    }


    
    

} 
