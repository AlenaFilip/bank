package com.telran.bank.controller;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.dto.TransactionDto;
import com.telran.bank.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Validated
@RequiredArgsConstructor
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Returns a filtered list of transactions", description = "Filter is possible for date, type(transfer, withdraw, deposit). It's possible to sort by dateTime. If there is no params - return all transactions")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of transactions", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TransactionDto.class)))
    })
    @ResponseStatus(OK)
    @GetMapping("/transactions")
    public List<TransactionDto> getAllTransactions(@RequestParam(value="date", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                   @RequestParam(value="type", required = false) String type,
                                                   @RequestParam(value="sort", required = false) String sort) {
        return transactionService.getListTransactions(date, type, sort);
    }

    @Operation(summary = "Returna transaction by id", description = "Getting an existing transaction by id")
    @ApiResponse(responseCode = "200", description = "Transaction returned", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))
    })
    @ResponseStatus(OK)
    @GetMapping("/transactions/{id}")
    public TransactionDto getTransaction(@PathVariable("id") Long id) {
        return transactionService.getTransaction(id);
    }
}
