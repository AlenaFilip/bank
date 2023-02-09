package com.telran.bank.sevice;

import com.telran.bank.entity.Transaction;
import com.telran.bank.exception.TransactionNotFoundException;
import com.telran.bank.mapper.TransactionMapper;
import com.telran.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;

    public List<Transaction> getAllTransactions(String date, String type, String sort) {
        return transactionRepository.findAll();
    }

    public Transaction getTransaction(Long id) {
        return  transactionRepository.findById(id)
                .orElseThrow(()-> new TransactionNotFoundException("id = " + id));
    }
}

