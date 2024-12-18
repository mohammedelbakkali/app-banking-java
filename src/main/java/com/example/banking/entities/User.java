package com.example.banking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User extends AbstractEntity{

    private String firsName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean active;
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Collection<Transaction> trasanctions;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;


    @OneToMany(mappedBy = "user")
    private Collection<Contact> contacts;


}
