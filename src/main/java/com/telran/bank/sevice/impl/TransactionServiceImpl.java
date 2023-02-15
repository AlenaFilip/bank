package com.telran.bank.sevice.impl;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Transaction;
import com.telran.bank.exception.TransactionNotFoundException;
import com.telran.bank.mapper.TransactionMapper;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.sevice.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;

    public Transaction getTransaction(Long id) {
        return  transactionRepository.findById(id)
                .orElseThrow(()-> new TransactionNotFoundException("id = " + id));
    }

    public List<TransactionDto> getTransactions(String date, List<String> type, String sort) {
        return transactionMapper.transactionsEntityToDto(transactionRepository.findAll());
    }

}


