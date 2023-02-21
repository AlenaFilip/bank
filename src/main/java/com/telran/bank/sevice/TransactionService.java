package com.telran.bank.sevice;

import com.telran.bank.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto getTransaction(Long id);

    List<TransactionDto> getListTransactions(String date, String type, String sort);
    }
