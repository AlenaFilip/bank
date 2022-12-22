package com.telran.bank.sevice;

import com.telran.bank.entity.Transaction;
import com.telran.bank.exception.TransactionNotFoundException;
import com.telran.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getAllTransactions(Map<String, String> allParams) {
        return transactions;
    }

    public Transaction getTransaction(Long id) {
        for (Transaction tr: transactions) {
            if (id.equals(tr.getId())) {
                return tr;
            }
        }
        throw new TransactionNotFoundException("Transaction not found");
    }
}

