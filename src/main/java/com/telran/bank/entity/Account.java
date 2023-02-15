package com.telran.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table (name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First Name is blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name is blank")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Country is blank")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "City is blank")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Creation Date is blank")
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "amount_of_money")
    private BigDecimal amountOfMoney;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return id +" email=" + email +" "+ firstName +" "+ lastName +" "+ country +" "+ city +" " + amountOfMoney;
    }
}
