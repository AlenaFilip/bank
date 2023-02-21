package com.telran.bank.sevice.impl;
import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;
import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.exception.AccountNotFoundException;
import com.telran.bank.mapper.AccountMapper;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.sevice.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account saveAccount (AccountDto accountDto) {
        Account account = accountMapper.accountDtoToEntity(accountDto);
        if(account.getCreationDate() == null) account.setCreationDate(LocalDate.now());
        return accountRepository.save(account);
    }
    @Override
    public List<AccountDto> getListAccounts(List<String> city, String creationDate, String sort){
        List<Account> accounts;
        if(city == null && creationDate == null) {
            accounts= accountRepository.findAll();
        } else if(city == null && creationDate != null) {
            accounts= accountRepository.findAllByCreationDate(LocalDate.parse(creationDate));
        } else if(city != null && creationDate == null) {
            accounts= accountRepository.findAllByCityInIgnoreCase(city);
        } else {
            accounts= accountRepository.findAllByCityInIgnoreCaseAndCreationDate(city, LocalDate.parse(creationDate));
        }
        if(sort != null && sort.toLowerCase().equals("creationdate")){
            accounts=accounts.stream()
                    .sorted((a1,a2) -> a1.getCreationDate().compareTo(a2.getCreationDate()))
                    .collect(Collectors.toList());
        } else if (sort != null && sort.toLowerCase().equals("creationdate")){
            accounts=accounts.stream()
                    .sorted((a1,a2) -> a2.getCreationDate().compareTo(a1.getCreationDate()))
                    .collect(Collectors.toList());
        }
        return accountMapper.accountsEntityToDto(accounts);
    }

    @Override
    public AccountDto getAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException("id = " + id));
        return accountMapper.accountEntityToDto(account);
    }

    @Transactional
    @Override
    public void transfer (Long fromId, Long toId, BigDecimal amount){
        if(fromId == null && toId == null) throw new RuntimeException("Transaction impossible - no accounts id");
        TransactionType transactionType = TransactionType.TRANSFER;
        if (fromId == null) {
            transactionType = TransactionType.DEPOSIT;
            fromId = toId;
        }
        if (toId == null) {
            transactionType = TransactionType.WITHDRAW;
            toId = fromId;
        }
        Account accountFrom = accountRepository.findById(fromId)
                .orElseThrow(()-> new AccountNotFoundException("fromId"));
        Account accountTo = accountRepository.findById(toId)
                .orElseThrow(()-> new AccountNotFoundException("toId"));
        switch (transactionType){
            case DEPOSIT: {
                accountTo.setAmountOfMoney(accountTo.getAmountOfMoney().add(amount));
                break;
            }
            case WITHDRAW:{
                if(accountFrom.getAmountOfMoney().compareTo(amount)<0) throw new RuntimeException("Not enough money");
                accountFrom.setAmountOfMoney(accountTo.getAmountOfMoney().subtract(amount));
                break;
            }
            case TRANSFER:{
                if(accountFrom.getAmountOfMoney().compareTo(amount)<0) throw new RuntimeException("Not enough money");
                accountFrom.setAmountOfMoney(accountTo.getAmountOfMoney().subtract(amount));
                accountTo.setAmountOfMoney(accountTo.getAmountOfMoney().add(amount));
                break;
            }
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setAccountFrom(fromId);
        newTransaction.setAccountTo(toId);
        newTransaction.setTransactionType(transactionType);
        newTransaction.setAmount(amount);
        transactionRepository.save(newTransaction);
    }

    @Override
    public Account updateAccount(Long id, AccountDto accountDto) {
        Account newAccount = accountMapper.accountDtoToEntity(accountDto);
        Account oldAccount = accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("id = " + id));
        if(newAccount.getFirstName() != null) oldAccount.setFirstName(newAccount.getFirstName());
        if(newAccount.getLastName() != null) oldAccount.setLastName(newAccount.getLastName());
        if(newAccount.getEmail() != null) oldAccount.setEmail(newAccount.getEmail());
        if(newAccount.getCountry() != null) oldAccount.setCountry(newAccount.getCountry());
        if(newAccount.getCity() != null) oldAccount.setCity(newAccount.getCity());
        if(newAccount.getCreationDate() != null) oldAccount.setCreationDate(newAccount.getCreationDate());
        if(newAccount.getAmountOfMoney() != null) oldAccount.setAmountOfMoney(newAccount.getAmountOfMoney());
        return accountRepository.save(oldAccount);
        }
    }

