package com.telran.bank.sevice;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts(List<String> city, String creationDate, String sort);

    Account getAccount(Long id);

    Account saveAccount(AccountDto accountDto);

    void transfer(String fromIdStr,String toIdStr,String amountStr);

    Account updateAccount(Long id,Account account);
}
