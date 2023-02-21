package com.telran.bank.repository;

import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByDateTime(LocalDate date);
    List<Transaction> findAllByTransactionType(TransactionType transactionType);
    List<Transaction> findAllByDateTimeContainsAndTransactionType(String date, TransactionType transactionType);

}
