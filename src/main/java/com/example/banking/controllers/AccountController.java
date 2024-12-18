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

import com.example.banking.DTO.AccountDto;
import com.example.banking.services.AccountService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

     @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AccountDto accountDto) {
       
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(accountService.save(accountDto));
    }

    @GetMapping("/")
    public ResponseEntity<Set<AccountDto>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

   
    

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        accountService.delete(id);
         return ResponseEntity.accepted().build();
    }
}
