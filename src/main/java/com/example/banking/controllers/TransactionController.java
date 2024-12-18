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
import com.example.banking.DTO.TransactionDto;
import com.example.banking.services.TransactionService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor

public class TransactionController {
    private final TransactionService transactionService;

        @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto) {
       
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(transactionService.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<Set<TransactionDto>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping("/find-all-transactions-by-user/{id}")
    public ResponseEntity<Set<TransactionDto>> findAllByUserId(@PathVariable("id")  Integer id){
         return ResponseEntity.ok(transactionService.findAllByUserId(id));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        transactionService.delete(id);
         return ResponseEntity.accepted().build();
    }


    
    

}
