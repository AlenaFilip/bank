package com.telran.bank.sevice;

import com.telran.bank.entity.Account;
import com.telran.bank.exception.AccountNotFoundException;
import com.telran.bank.mapper.AccountMapper;
import com.telran.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    public Account saveAccount (Account account){
        return accountRepository.save(account);
    }
    public List<Account> getAllAccounts(List<String> city, String creationDate, String sort){
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException("id = " + id));
    }
    public void transfer (String fromIdStr, String toIdStr, String amountStr){
    }
    public Account updateAccount(Long id, Account account) {
        return new Account();
    }
}
