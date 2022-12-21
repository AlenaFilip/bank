package com.telran.bank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String email;
    private String creationDate;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private Double amountOfMoney;


    public Long getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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
        return Objects.equals(getId(), account.getId()) && Objects.equals(getEmail(), account.getEmail()) && Objects.equals(getCreationDate(), account.getCreationDate()) && Objects.equals(getFirstName(), account.getFirstName()) && Objects.equals(getLastName(), account.getLastName()) && Objects.equals(getCountry(), account.getCountry()) && Objects.equals(getCity(), account.getCity()) && Objects.equals(getAmountOfMoney(), account.getAmountOfMoney());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getCreationDate(), getFirstName(), getLastName(), getCountry(), getCity(), getAmountOfMoney());
    }

    @Override
    public String toString() {
        return id +" email=" + email +" "+  firstName +" "+ lastName +" "+ country +" "+ city +" " + amountOfMoney;
    }
}
