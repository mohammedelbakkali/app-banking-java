package com.example.banking.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.DTO.UserDto;
import com.example.banking.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final  UserService userService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
        @RequestBody   UserDto userDto
    ){
        return ResponseEntity.ok(userService.save(userDto));         
    }

    @GetMapping("/")
    public ResponseEntity<Set<UserDto>> findAll(){
         return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id){
         return ResponseEntity.ok(userService.findById(id));
    }

    @PatchMapping("/validate/{id}")
    public ResponseEntity<Integer> validateAccount(@PathVariable("id") Integer id){
         return ResponseEntity.ok(userService.validateAccount(id));
    }

    @PatchMapping("/invalidate/{id}")
    public ResponseEntity<Integer> invalidateAccount(@PathVariable("id") Integer id){
         return ResponseEntity.ok(userService.invalidateAccount(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")  Integer id){
          userService.delete(id);
          return ResponseEntity.accepted().build();
    }


}
