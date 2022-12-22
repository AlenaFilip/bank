package com.telran.bank.entity;

import jakarta.persistence.*;

import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
@Table (name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String email;
    private GregorianCalendar creationDate;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private Double amountOfMoney;


    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getAmountOfMoney() { return amountOfMoney;}

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getEmail(), account.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    @Override
    public String toString() {
        return id +" email=" + email +" "+  firstName +" "+ lastName +" "+ country +" "+ city +" " + amountOfMoney;
    }
}
