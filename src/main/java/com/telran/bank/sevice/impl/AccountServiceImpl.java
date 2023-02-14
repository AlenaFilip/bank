package com.telran.bank.sevice.impl;
import com.telran.bank.entity.Account;
import com.telran.bank.exception.AccountNotFoundException;
import com.telran.bank.mapper.AccountMapper;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.sevice.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
        @Autowired
        private AccountRepository accountRepository;
        @Autowired
        private TransactionRepository transactionRepository;

        private final AccountMapper accountMapper;

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
