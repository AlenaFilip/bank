package com.telran.bank.sevice;

import com.telran.bank.dto.TransactionDto;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    TransactionDto getTransaction(Long id);

    List<TransactionDto> getListTransactions(LocalDate date, String type, String sort);
    }
