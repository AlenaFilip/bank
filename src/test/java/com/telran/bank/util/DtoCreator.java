package com.telran.bank.util;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.bank.dto.AccountDto;
import com.telran.bank.dto.TransactionDto;
import com.telran.bank.entity.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

public class DtoCreator {
    public static AccountDto getAccountDTO(){
        return new AccountDto(
                "Anna",
                "Progov",
                "anna@mail.com",
                "Germany",
                "Bremen",
                "2020-12-12",
                "12000"
        );
    }

    public static List<AccountDto> getListAccountDto(int order) {
        AccountDto ad1 = new AccountDto(null,null,null,null,null,
                "2023-01-01",null);
        AccountDto ad2 = new AccountDto(null,null,null,null,null,
                "2023-01-02",null);
        AccountDto ad3 = new AccountDto(null,null,null,null,null,
                "2023-01-03",null);
        if(order==1) return new ArrayList<>(List.of(ad1,ad2,ad3));
        if(order==-1) return new ArrayList<>(List.of(ad3,ad2,ad1));
        return new ArrayList<>(List.of(ad2,ad3,ad1));
    }

    public static TransactionDto getTransactionDTO(){
        return new TransactionDto(
                "WITHDRAW",
                "2",
                "4",
                "48",
                "2021-05-21T00:01:00"
        );
    }

    public static List<TransactionDto> getListTransactionDto(int order) {
        TransactionDto td1 = new TransactionDto(null,null,null,null,"2023-01-01T00:00:00");
        TransactionDto td2 = new TransactionDto(null,null,null,null,"2023-01-02T00:00:00");
        TransactionDto td3 = new TransactionDto(null,null,null,null,"2023-01-03T00:00:00");
        if(order==1) return new ArrayList<>(List.of(td1,td2,td3));
        if(order==-1) return new ArrayList<>(List.of(td3,td2,td1));
        return new ArrayList<>(List.of(td2,td3,td1));
    }

}
