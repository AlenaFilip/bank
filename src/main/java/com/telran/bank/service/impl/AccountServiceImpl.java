package com.telran.bank.service.impl;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;
import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.exception.AccountNotFoundException;
import com.telran.bank.exception.ErrorMessage;
import com.telran.bank.exception.TransactionFailedException;
import com.telran.bank.mapper.AccountMapper;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telran.bank.entity.enums.TransactionType.*;

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
        if(city != null && creationDate != null) {   // city + date
            accounts= accountRepository.findAllByCityInIgnoreCaseAndCreationDate(city, LocalDate.parse(creationDate));
        } else if(creationDate != null) {  // only creationDate
            accounts= accountRepository.findAllByCreationDate(LocalDate.parse(creationDate));
        } else if(city != null) {  // only city
            accounts= accountRepository.findAllByCityInIgnoreCase(city);
        } else {   // without filter
            accounts= accountRepository.findAll();
        }
        if(sort != null){  // with order
            if(sort.equalsIgnoreCase("creationDate")) {
                accounts = accounts.stream()
                        .sorted(Comparator.comparing(Account::getCreationDate))
                        .collect(Collectors.toList());
            } else if (sort.equalsIgnoreCase("-creationDate")) {
                accounts = accounts.stream()
                        .sorted(Comparator.comparing(Account::getCreationDate, Comparator.reverseOrder()))
                        .collect(Collectors.toList());
            }
        }
        List<AccountDto> list = accountMapper.accountsEntityToDto(accounts);
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
        TransactionType transactionType = checkTransactionType(fromId,toId);
        Long accFrom = (fromId == null)? toId: fromId;
        Long accTo = (toId == null)? fromId: toId;
        Account accountFrom = accountRepository.findById(accFrom)
                .orElseThrow(()->new AccountNotFoundException("id = " + accFrom));
        Account accountTo = accountRepository.findById(accTo)
                .orElseThrow(()->new AccountNotFoundException("id = " + accTo));
        if(transactionType == DEPOSIT) {
            accountTo.setAmountOfMoney(accountTo.getAmountOfMoney().add(amount));
            accountRepository.save(accountTo);
        }
        if(transactionType == WITHDRAW){
            if(accountFrom.getAmountOfMoney().compareTo(amount)<0) throw new TransactionFailedException(ErrorMessage.NOT_ENOUGH_MONEY);
            accountFrom.setAmountOfMoney(accountTo.getAmountOfMoney().subtract(amount));
            accountRepository.save(accountFrom);
        }
        if(transactionType == TRANSFER){
            if(accountFrom.getAmountOfMoney().compareTo(amount)<0) throw new TransactionFailedException(ErrorMessage.NOT_ENOUGH_MONEY);
            accountFrom.setAmountOfMoney(accountFrom.getAmountOfMoney().subtract(amount));
            accountTo.setAmountOfMoney(accountTo.getAmountOfMoney().add(amount));
            accountRepository.save(accountFrom);
            accountRepository.save(accountTo);
        }
        Transaction transaction = new Transaction();
        transaction.setAccountFrom(accFrom);
        transaction.setAccountTo(accTo);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }

    public TransactionType checkTransactionType(Long fromId, Long toId){
        TransactionType transactionType = TRANSFER;
        if(fromId == null && toId == null) throw new TransactionFailedException(ErrorMessage.TRANSACTION_IMPOSSIBLE);
        if (fromId == null) {
            transactionType = DEPOSIT;
        }
        if (toId == null) {
            transactionType = WITHDRAW;
        }
        return transactionType;
    }
    @Override
    public Account updateAccount(Long id, AccountDto accountDto) {
        Account newFieldsAccount = accountMapper.accountDtoToEntity(accountDto);
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("id = " + id));
        if(newFieldsAccount.getFirstName() != null) account.setFirstName(newFieldsAccount.getFirstName());
        if(newFieldsAccount.getLastName() != null) account.setLastName(newFieldsAccount.getLastName());
        if(newFieldsAccount.getEmail() != null) account.setEmail(newFieldsAccount.getEmail());
        if(newFieldsAccount.getCountry() != null) account.setCountry(newFieldsAccount.getCountry());
        if(newFieldsAccount.getCity() != null) account.setCity(newFieldsAccount.getCity());
        if(newFieldsAccount.getCreationDate() != null) account.setCreationDate(newFieldsAccount.getCreationDate());
        if(newFieldsAccount.getAmountOfMoney() != null) account.setAmountOfMoney(newFieldsAccount.getAmountOfMoney());
        return accountRepository.save(account);
        }
    }
