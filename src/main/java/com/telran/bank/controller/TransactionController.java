package com.telran.bank.controller;

import com.telran.bank.entity.Transaction;
import com.telran.bank.sevice.TransactionService;
import com.telran.bank.sevice.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(@RequestParam(required = false) String date,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(required = false) String sort) {
        return transactionServiceImpl.getTransactions(date, type, sort);
    }
    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable("id") Long id) {
        return transactionServiceImpl.getTransaction(id);
    }
}
