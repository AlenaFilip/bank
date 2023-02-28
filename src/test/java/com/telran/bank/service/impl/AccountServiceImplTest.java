package com.telran.bank.service.impl;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;
import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.exception.AccountNotFoundException;
import com.telran.bank.mapper.AccountMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Test class for AccountServiceImpl")
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountMapper accountMapper;
    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    @DisplayName("Test creating Account")
    void saveAccountTest() {
        AccountDto accountDto = DtoCreator.getAccountDTO();
        Account account = EntityCreator.getAccount();
        when(accountRepository.save(account)).thenReturn(account);
        when(accountMapper.accountDtoToEntity(accountDto)).thenReturn(account);
        assertEquals(account, accountService.saveAccount(accountDto));
    }

    @Test
    @DisplayName("Test getting sorted accounts list ")
    void getListAccountsTest() {
        List<Account> list = EntityCreator.getListAccount(0);
        List<Account> list1 = EntityCreator.getListAccount(1);
        List<Account> list_1 = EntityCreator.getListAccount(-1);
        when(accountRepository.findAll()).thenReturn(list);
        when(accountMapper.accountsEntityToDto(list1)).thenReturn(DtoCreator.getListAccountDto(1));
        when(accountMapper.accountsEntityToDto(list_1)).thenReturn(DtoCreator.getListAccountDto(-1));
        List<AccountDto> result1 = accountService.getListAccounts(null,null,"creationDate");
        Assertions.assertIterableEquals(DtoCreator.getListAccountDto(1),result1);
        List<AccountDto> result_1 = accountService.getListAccounts(null,null,"-creationDate");
        Assertions.assertIterableEquals(DtoCreator.getListAccountDto(-1),result_1);
    }

    @Test
    @DisplayName("Test get Account by Id:  id exists")
    void getAccountExistIdTest() {
        Long id = 1L;
        Account account = EntityCreator.getAccount();
        AccountDto expectedDto = DtoCreator.getAccountDTO();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        when(accountMapper.accountEntityToDto(account)).thenReturn(expectedDto);
        AccountDto actualDto = accountService.getAccount(id);
        Assertions.assertEquals(expectedDto, actualDto);
    }

    @Test
    @DisplayName("Test get Account by Id:  id not exists")
    void getAccountNotExistIdTest() {
        Assertions.assertThrows(AccountNotFoundException.class,
                () -> accountService.getAccount(55L));
    }

    @Test
    @DisplayName("Test transfer ")
    void transferTest() {

    }

    @Test
    @DisplayName("Test checkTransactionType method")
    void checkTransactionTypeTest() {
        assertEquals(TransactionType.DEPOSIT,accountService.checkTransactionType(null,5L));
        assertEquals(TransactionType.WITHDRAW,accountService.checkTransactionType(5L,null));
        assertEquals(TransactionType.TRANSFER,accountService.checkTransactionType(1L,5L));
    }

    @Test
    void updateAccount() {
    }
}