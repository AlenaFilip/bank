package com.telran.bank.controller;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;
import com.telran.bank.sevice.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveAccount (@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(@RequestParam(required = false) List<String> cities,
                                        @RequestParam(required = false) String creationDate,
                                        @RequestParam(required = false) String sort){
        return accountService.getAllAccounts(cities, creationDate, sort);
    }

    @GetMapping("/accounts/{id}")                                                                            //3
    public Account getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @PutMapping("/accounts")
    public void transfer (@RequestParam("from") String fromIdStr,
                          @RequestParam("to") String toIdStr,
                          @RequestParam("amount") String amountStr){
        accountService.transfer(fromIdStr,toIdStr,amountStr);
    }

    @PatchMapping("/accounts/{id}")                                                                            //5
    public Account updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
        return accountService.updateAccount(id,account);
    }
}
