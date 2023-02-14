package com.telran.bank.entity;

import com.telran.bank.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table (name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "")
    private TransactionType transactionType;

    @Column(name = "")
    private Long accountFrom;

    @Column(name = "")
    private Long accountTo;

    @Column(name = "")
    private BigDecimal amount;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

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

    @Override
    public String toString() {
        return id +
                "  " + dateTime +
                "  " + transactionType +
                " accountFrom=" + accountFrom +
                " accountTo=" + accountTo +
                " amount=" + amount;
    }
}
