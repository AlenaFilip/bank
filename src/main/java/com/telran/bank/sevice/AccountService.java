package com.telran.bank.sevice;

import com.telran.bank.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts(List<String> cities, String creationDate, String sort);

    Account getAccount(Long id);

    Account saveAccount(Account account);

    void transfer(String fromIdStr,String toIdStr,String amountStr);

    Account updateAccount(Long id,Account account);
}
