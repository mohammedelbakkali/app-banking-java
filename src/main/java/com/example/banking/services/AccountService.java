package com.example.banking.services;

import com.example.banking.DTO.AccountDto;

import java.util.Optional;

public interface AccountService extends AbstractService<AccountDto>{

    String generateRandomIban();

}
