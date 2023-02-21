package com.telran.bank.controller;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;
import com.telran.bank.sevice.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveAccount (@RequestBody AccountDto accountDto){
        return accountService.saveAccount(accountDto);
    }

    @GetMapping("/accounts")
    public List<AccountDto> getAllAccounts(@RequestParam(value="city", required = false) List<String> city,
                                           @RequestParam(value="date", required = false) String creationDate,
                                           @RequestParam(value="sort", required = false) String sort){
        return accountService.getListAccounts(city, creationDate, sort);
    }

    @GetMapping("/accounts/{id}")                                                                            //3
    public AccountDto getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @PutMapping("/accounts")
    public void transfer (@RequestParam(value="from", required = false) Long fromId,
                          @RequestParam(value="to", required = false) Long toId,
                          @RequestParam(value="amount",required = true ) BigDecimal amount){
        accountService.transfer(fromId, toId, amount);
    }

    @PatchMapping("/accounts/{id}")                                                                            //5
    public Account updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {
        return accountService.updateAccount(id,accountDto);
    }
}
