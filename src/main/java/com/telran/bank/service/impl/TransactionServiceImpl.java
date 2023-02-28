package com.telran.bank.service.impl;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.exception.TransactionNotFoundException;
import com.telran.bank.mapper.TransactionMapper;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionDto getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new TransactionNotFoundException("id = " + id));
        return transactionMapper.transactionEntityToDto(transaction);
    }

    @Override
    public List<TransactionDto> getListTransactions(LocalDate date, String type, String sort) {
        if(type != null) type = type.toUpperCase();
        LocalDateTime dateMin = null;
        LocalDateTime dateMax = null;
        if(date != null) {
            dateMin = date.atTime(LocalTime.MIN);
            dateMax = date.atTime(LocalTime.MAX);
        }
        List<Transaction> transactions;
        if(date == null && type == null){
            transactions = transactionRepository.findAll();
        } else if(date == null){   // only type
            transactions = transactionRepository.findAllByTransactionType(TransactionType.valueOf(type));
        } else if(type == null){    // only date
            transactions = transactionRepository.findAllByDateTimeIsBetween(dateMin, dateMax);
        } else {                // type and date
            transactions = transactionRepository.findAllByTransactionTypeAndDateTimeIsBetween(TransactionType.valueOf(type), dateMin, dateMax);
        }
        if(sort != null){
            if(sort.equalsIgnoreCase("dateTime")) {
                transactions = transactions.stream()
                        .sorted(Comparator.comparing(Transaction::getDateTime))
                        .collect(Collectors.toList());
            }
            if (sort.equalsIgnoreCase("-dateTime")) {
                transactions = transactions.stream()
                        .sorted(Comparator.comparing(Transaction::getDateTime,Comparator.reverseOrder()))
                        .collect(Collectors.toList());
            }
        }
        return transactionMapper.transactionsEntityToDto(transactions);
    }
}


