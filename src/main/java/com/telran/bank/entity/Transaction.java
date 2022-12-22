package com.telran.bank.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
@Table (name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private GregorianCalendar dateTime;
    private TransactionType transactionType;
    private Long accountFrom;
    private Long accountTo;
    private Double amount;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public GregorianCalendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(GregorianCalendar dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
