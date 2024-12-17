package com.example.banking.services;

import com.example.banking.DTO.TransactionDto;
import com.example.banking.entities.TransactionType;

import java.util.List;
import java.util.Set;

public interface TransactionService extends AbstractService<TransactionDto>{
    int getTransactionMultiplier(TransactionType type);
    Set<TransactionDto> findAllByUserId(Integer id);
}
