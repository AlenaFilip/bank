package com.telran.bank.dto;

import com.telran.bank.entity.enums.TransactionType;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class TransactionDto {

    String transactionType;

    String accountFrom;

    String accountTo;

    String amount;

    String dateTime;
}
