package com.example.banking.services.implementationsServices;

import com.example.banking.DTO.TransactionDto;
import com.example.banking.Repositories.TransactionRepository;
import com.example.banking.entities.Transaction;
import com.example.banking.entities.TransactionType;
import com.example.banking.helpers.ObjectValidator;
import com.example.banking.services.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ObjectValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto objDto) {
        validator.validate(objDto);
        Transaction transaction = TransactionDto.toEntity(objDto);
        BigDecimal amount = transaction.getAmount().multiply(
                 BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()))
        );
        transaction.setAmount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public Set<TransactionDto> findAll() {
        return transactionRepository
                .findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository
                .findById(id).map(TransactionDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No transaction was found with id="+id));
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public int getTransactionMultiplier(TransactionType type) {
        return TransactionType.TRANSFERT == type ? -1:1 ;
    }

    @Override
    public Set<TransactionDto> findAllByUserId(Integer id) {
        return transactionRepository
                .findAllByUserId(id)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toSet());
    }
}
