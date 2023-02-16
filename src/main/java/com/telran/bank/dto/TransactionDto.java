package com.telran.bank.dto;

import lombok.Value;

@Value
public class TransactionDto {

    String transactionType;

    String accountFrom;

    String accountTo;

    String amount;

    String dateTime;
}
