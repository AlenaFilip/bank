package com.telran.bank.util;
import com.telran.bank.entity.Account;
import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntityCreator {
    public static Account getAccount(){
        return new Account(
                1L,
                "Anna",
                "Progov",
                "anna@mail.com",
                "Germany",
                "Bremen",
                LocalDate.parse("2020-12-12"),
                BigDecimal.valueOf(12000L)
        );
    }

    public static List<Account> getListAccount(int order) {
        Account account1 = new Account();
        account1.setId(1L);
        account1.setCreationDate(LocalDate.parse("2023-01-01"));
        Account account2 = new Account();
        account2.setId(2L);
        account2.setCreationDate(LocalDate.parse("2023-01-02"));
        Account account3 = new Account();
        account3.setId(3L);
        account3.setCreationDate(LocalDate.parse("2023-01-03"));
        if(order==1) return new ArrayList<>(List.of(account1,account2,account3));
        if(order==-1) return new ArrayList<>(List.of(account3,account2,account1));
        return new ArrayList<>(List.of(account2,account3,account1));
    }

    public static Transaction getTransaction(){
        return new Transaction(
                1L,
                TransactionType.WITHDRAW,
                2L,
                4L,
                BigDecimal.valueOf(48L),
                LocalDateTime.parse("2021-05-21T00:01:00")
        );
    }

    public static List<Transaction> getListTransaction(int order) {
        Transaction tr1  = new Transaction();
        tr1.setId(1L);
        tr1.setDateTime(LocalDateTime.parse("2023-01-01T00:00:00"));
        Transaction tr2  = new Transaction();
        tr2.setId(2L);
        tr2.setDateTime(LocalDateTime.parse("2023-01-02T00:00:00"));
        Transaction tr3  = new Transaction();
        tr3.setId(3L);
        tr3.setDateTime(LocalDateTime.parse("2023-01-03T00:00:00"));
        if(order==1) return new ArrayList<>(List.of(tr1,tr2,tr3));
        if(order==-1) return new ArrayList<>(List.of(tr3,tr2,tr1));
        return new ArrayList<>(List.of(tr2,tr3,tr1));
    }

}
