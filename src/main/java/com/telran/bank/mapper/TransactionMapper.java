package com.telran.bank.mapper;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto transactionEntityToDto(Transaction transaction);

    Transaction transactionDtoToEntity(TransactionDto transactionDto);

    List<TransactionDto> transactionsEntityToDto(List<Transaction> transactions);
}
