package com.telran.bank.service.impl;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Account;
import com.telran.bank.entity.Transaction;
import com.telran.bank.exception.AccountNotFoundException;
import com.telran.bank.exception.TransactionNotFoundException;
import com.telran.bank.mapper.AccountMapper;
import com.telran.bank.mapper.TransactionMapper;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.util.DtoCreator;
import com.telran.bank.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@DisplayName("Test class for TransactionServiceImpl")
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    TransactionMapper transactionMapper;

    @InjectMocks
    TransactionServiceImpl transactionService;


    @Test
    void getTransaction() {
    }

    @Test
    @DisplayName("Test get Transaction by Id:  id exists")
    void getTransactionExistIdTest() {
        Long id = 1L;
        Transaction transaction = EntityCreator.getTransaction();
        TransactionDto expectedDto = DtoCreator.getTransactionDTO();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        when(transactionMapper.transactionEntityToDto(transaction)).thenReturn(expectedDto);
        TransactionDto actualDto = transactionService.getTransaction(id);
        Assertions.assertEquals(expectedDto, actualDto);
    }

    @Test
    @DisplayName("Test get Transaction by Id:  id not exists")
    void getTransactionNotExistIdTest() {
        Assertions.assertThrows(TransactionNotFoundException.class,
                () -> transactionService.getTransaction(5L));
    }

    @Test
    @DisplayName("Test getting sorted transactions list ")
    void getListTransactions() {
        List<Transaction> list = EntityCreator.getListTransaction(0);
        List<Transaction> list1 = EntityCreator.getListTransaction(1);
        List<Transaction> list_1 = EntityCreator.getListTransaction(-1);
        when(transactionRepository.findAll()).thenReturn(list);
        when(transactionMapper.transactionsEntityToDto(list1)).thenReturn(DtoCreator.getListTransactionDto(1));
        when(transactionMapper.transactionsEntityToDto(list_1)).thenReturn(DtoCreator.getListTransactionDto(-1));
        List<TransactionDto> result1 = transactionService.getListTransactions(null,null,"dateTime");
        Assertions.assertIterableEquals(DtoCreator.getListTransactionDto(1),result1);
        List<TransactionDto> result_1 = transactionService.getListTransactions(null,null,"-dateTime");
        Assertions.assertIterableEquals(DtoCreator.getListTransactionDto(-1),result_1);
    }
}