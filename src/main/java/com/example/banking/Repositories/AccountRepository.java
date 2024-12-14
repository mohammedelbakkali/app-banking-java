package com.example.banking.Repositories;

import com.example.banking.entities.Account;
import com.example.banking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    //Spring Data pattern SDP

    Optional<Account> findByIban(String iban);

}
