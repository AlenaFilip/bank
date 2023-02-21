package com.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

@Value
public class TransactionDto {

    String transactionType;

    String accountFrom;

    String accountTo;

    String amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    String dateTime;
}
