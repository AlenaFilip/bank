package com.telran.bank.controller;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Transaction;
import com.telran.bank.sevice.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/transactions")
    public List<TransactionDto> getAllTransactions(@RequestParam(required = false) String date,
                                                   @RequestParam(required = false) List<String> type,
                                                   @RequestParam(required = false) String sort) {
        return transactionServiceImpl.getTransactions(date, type, sort);
    }
    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable("id") Long id) {
        return transactionServiceImpl.getTransaction(id);
    }
}
