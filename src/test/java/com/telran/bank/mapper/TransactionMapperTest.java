package com.telran.bank.mapper;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Transaction;
import com.telran.bank.util.DtoCreator;
import com.telran.bank.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TransactionMapperTest {


    private final TransactionMapper mapper = new TransactionMapperImpl();

    @DisplayName("Test mapping Transaction to TransactionDto")
    @Test
    void transactionEntityToDtoTest() {
        Transaction transaction = EntityCreator.getTransaction();
        TransactionDto expectedDto = DtoCreator.getTransactionDTO();
        TransactionDto actualDto = mapper.transactionEntityToDto(transaction);
        assertEquals(expectedDto.toString(), actualDto.toString());
    }

    @DisplayName("Test mapping Transaction to TransactionDto")
    @Test
    void transactionDtoToEntityTest() {
        TransactionDto transactionDto = DtoCreator.getTransactionDTO();
        Transaction expectedEntity = EntityCreator.getTransaction();
        Transaction actualEntity = mapper.transactionDtoToEntity(transactionDto);
        assertEquals(expectedEntity.toString(),actualEntity.toString());
    }

    @DisplayName("Test mapping List Transaction to List TransactionDto")
    @Test
    void transactionsEntityToDtoTest() {
        Transaction transaction = EntityCreator.getTransaction();
        List<Transaction> transactions = List.of(transaction);
        TransactionDto transactionDto = DtoCreator.getTransactionDTO();
        List<TransactionDto> expectedList = List.of(transactionDto);
        List<TransactionDto> actualList = mapper.transactionsEntityToDto(transactions);
        assertEquals(expectedList.get(0).toString(), actualList.get(0).toString());
    }
}