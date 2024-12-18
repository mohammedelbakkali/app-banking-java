package com.example.banking.Repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banking.entities.Transaction;
import com.example.banking.entities.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Set<Transaction> findAllByUserId(Integer id);

    @Query("select  sum(t.amount) from Transaction t where t.user.id =:userId")
    public BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id=:userId and t.type =:transactionType")
    BigDecimal findHighestByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.createdDate , sum(t.amount) from Transaction  t where t.user.id =:userId and t.createdDate between :startDate and :endDate group by t.createdDate order by t.createdDate")
    public Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDateTime startDate, LocalDateTime endDate , Integer userId);
}
