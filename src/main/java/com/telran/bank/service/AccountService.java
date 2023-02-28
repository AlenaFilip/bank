package com.telran.bank.service;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<AccountDto> getListAccounts(List<String> city, String creationDate, String sort);

    AccountDto getAccount(Long id);

    Account saveAccount(AccountDto accountDto);

    void transfer(Long fromId, Long toId, BigDecimal amount);

    Account updateAccount(Long id,AccountDto accountDto);
}
