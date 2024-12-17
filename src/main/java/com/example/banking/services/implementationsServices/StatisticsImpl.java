package com.example.banking.services.implementationsServices;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.banking.Repositories.TransactionRepository;
import com.example.banking.entities.TransactionType;
import com.example.banking.services.StatisticsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsImpl implements StatisticsService{

    private final TransactionRepository transactionRepository;


    @Override
    public Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDate startDate, LocalDate endDate , Integer userId) {
        LocalDateTime start = LocalDateTime.of(startDate,LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(endDate,LocalTime.of(23,59,59));
        return  transactionRepository.findSumTransactionByDate(start, end , userId);
    }

    @Override  
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
         return transactionRepository.findHighestByTransactionType(userId,TransactionType.TRANSFERT);
      }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestByTransactionType(userId,TransactionType.DEPOSIT);
    }
    
}
