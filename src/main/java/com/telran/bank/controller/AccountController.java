package com.telran.bank.controller;

import com.telran.bank.entity.Account;
import com.telran.bank.sevice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/accounts")
    public Account saveAccount (@RequestBody Account account){
        return accountService.saveAccount(account);
    }
    @GetMapping("/accounts")
    public List<Account> getAllAccounts(@RequestParam Map<String, String> allParams){
        return accountService.getAllAccounts(allParams);
    }
    @GetMapping("/accounts/{id}")                                                                            //3
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }
    @PutMapping("/accounts")
    public void transfer (@RequestParam("from") String fromIdStr,
                          @RequestParam("to") String toIdStr,
                          @RequestParam("amount") String amountStr){
        accountService.transfer(fromIdStr,toIdStr,amountStr);
    }
    @PatchMapping("/accounts/{id}")                                                                            //5
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id,account);
    }
}
