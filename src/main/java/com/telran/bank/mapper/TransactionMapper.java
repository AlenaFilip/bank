package com.telran.bank.mapper;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Transaction;
import com.telran.bank.sevice.impl.AccountServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


//    @Mapping(source = "account.id", target = "accountFrom")
//    @Mapping(target = "account_from", expression = "")

    TransactionDto transactionEntityToDto(Transaction transaction);

    Transaction transactionDtoToEntity(TransactionDto transactionDto);

    List<TransactionDto> transactionsEntityToDto(List<Transaction> transactions);
}
