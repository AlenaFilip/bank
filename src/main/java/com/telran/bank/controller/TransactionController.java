package com.telran.bank.controller;

import com.telran.bank.entity.Transaction;
import com.telran.bank.sevice.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(@RequestParam Map<String, String> allParams) {
        return transactionService.getAllTransactions(allParams);
    }
    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }
}
