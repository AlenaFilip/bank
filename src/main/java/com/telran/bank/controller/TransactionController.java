package com.telran.bank.controller;

import com.telran.bank.dto.TransactionDto;
import com.telran.bank.sevice.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<TransactionDto> getAllTransactions(@RequestParam(value="date", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                   @RequestParam(value="type", required = false) String type,
                                                   @RequestParam(value="sort", required = false) String sort) {
        return transactionService.getListTransactions(date, type, sort);
    }
    @GetMapping("/transactions/{id}")
    public TransactionDto getTransaction(@PathVariable("id") Long id) {
        return transactionService.getTransaction(id);
    }
}
