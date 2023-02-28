package com.telran.bank.controller;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.Account;
import com.telran.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Creating an account", description = "The body contents email, firstname, lastname, country and city to create an account.")
    @ApiResponse(responseCode = "201", description = "Account is created", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))
    })
    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveAccount (@RequestBody AccountDto accountDto){
        return accountService.saveAccount(accountDto);
    }

    @Operation(summary = "Returns a filtered list of accounts", description = "Filter is possible for date, city. It's possible to sort by creationDate. If there is no params - return all account")
    @ApiResponse(responseCode = "200", description = "Returned the list of accounts", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountDto.class)))
    })
    @GetMapping("/accounts")
    @ResponseStatus(OK)
    public List<AccountDto> getAllAccounts(@RequestParam(value="city", required = false) List<String> city,
                                           @RequestParam(value="date", required = false) String creationDate,
                                           @RequestParam(value="sort", required = false) String sort){
        return accountService.getListAccounts(city, creationDate, sort);
    }

    @Operation(summary = "Returna account by id", description = "Getting an existing account by id")
    @ApiResponse(responseCode = "200", description = "Account returned", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))
    })
    @GetMapping("/accounts/{id}")
    @ResponseStatus(OK)
    public AccountDto getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @Operation(summary = "Create a transaction. When 2 account - transfer, otherwise - deposit or withdraw ", description = "Enter 3 parametrs: from, to, ammount")
    @ApiResponse(responseCode = "200", description = "Transaction is created", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TransactionDto.class)))
    })
    @PutMapping("/accounts")
    @ResponseStatus(OK)
    public void transfer (@RequestParam(value="from", required = false) Long fromId,
                          @RequestParam(value="to", required = false) Long toId,
                          @RequestParam(value="amount",required = true ) BigDecimal amount){
        accountService.transfer(fromId, toId, amount);
    }

    @Operation(summary = "Updating an account", description = "The body content email, firstname, lastname, country and city to update an account. Parametr - account ID")
    @ApiResponse(responseCode = "201", description = "Account is created", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountDto.class))
    })
    @PatchMapping("/accounts/{id}")
    @ResponseStatus(OK)
    public Account updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {
        return accountService.updateAccount(id,accountDto);
    }
}
