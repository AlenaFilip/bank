package com.telran.bank.entity;

import com.telran.bank.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table (name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_type")
    private TransactionType transactionType;


    @ManyToOne
    @JoinColumn(name="account_from", referencedColumnName = "id")
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to", referencedColumnName = "id")
    private Account accountTo;

//    @Column(name = "account_from")
//    private Long accountFrom;
//
//    @Column(name = "account_to")
//    private Long accountTo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date_time")
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
