package com.telran.bank.sevice.impl;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.exception.TransactionNotFoundException;
import com.telran.bank.mapper.TransactionMapper;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.sevice.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<TransactionDto> getListTransactions(String date, String type, String sort) {

        List<Transaction> transactions;
        if(date == null && type == null){
            transactions = transactionRepository.findAll();
        } else if(date == null && type != null){
            transactions = transactionRepository.findAllByTransactionType(TransactionType.valueOf(type));
        } else if(date != null && type == null){
            transactions = transactionRepository.findAllByDateTime(LocalDate.parse(date));
        } else {
            transactions = transactionRepository.findAllByDateTimeContainsAndTransactionType(date, TransactionType.valueOf(type));
        }
        if(sort != null && sort.toLowerCase().equals("datetime")){
            transactions=transactions.stream()
                    .sorted((a1,a2) -> a1.getDateTime().compareTo(a2.getDateTime()))
                    .collect(Collectors.toList());
        } else if (sort != null && sort.toLowerCase().equals("-datetime")){
            transactions=transactions.stream()
                    .sorted((a1,a2) -> a2.getDateTime().compareTo(a1.getDateTime()))
                    .collect(Collectors.toList());
        }
        return transactionMapper.transactionsEntityToDto(transactions);
    }
}


