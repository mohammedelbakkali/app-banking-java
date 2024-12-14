package com.example.banking.services;

import com.example.banking.DTO.TransactionDto;
import com.example.banking.entities.TransactionType;

public interface TransactionService extends AbstractService<TransactionDto>{
    int getTransactionMultiplier(TransactionType type);
}
