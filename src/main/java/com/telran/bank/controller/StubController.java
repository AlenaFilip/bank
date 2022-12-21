package com.telran.bank.controller;

import com.telran.bank.entity.Account;
import com.telran.bank.entity.Transaction;
import com.telran.bank.sevice.StubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StubController {
    @Autowired
    private StubService stubService;
    @PostMapping("/accounts")
    public Account saveAccount (@RequestBody Account account){
        return stubService.saveAccount(account);
    }
    @GetMapping("/accounts")
    public List<Account> getAllAccounts(@RequestParam Map<String, String> allParams){
        return stubService.getAllAccounts(allParams);
    }
    @GetMapping("/accounts/{id}")                                                                            //3
    public Account getAccount(@PathVariable Long id) {
        return stubService.getAccount(id);
    }
    @PutMapping("/accounts")
    public void transfer (@RequestParam("from") String fromIdStr,
                          @RequestParam("to") String toIdStr,
                          @RequestParam("amount") String amountStr){
        stubService.transfer(fromIdStr,toIdStr,amountStr);
    }
    @PatchMapping("/accounts/{id}")                                                                            //5
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return stubService.updateAccount(id,account);
    }
    @GetMapping("/transactions")                                                                            //7
    public List<Transaction> getAllTransactions(@RequestParam Map<String, String> allParams) {
        return stubService.getAllTransactions(allParams);
    }
    @GetMapping("/transactions/{id}")                                                                            //7
    public Transaction getTransaction(@PathVariable Long id) {
        return stubService.getTransaction(id);
    }
}
