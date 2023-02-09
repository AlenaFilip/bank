package com.telran.bank.dto;

import lombok.Value;

import java.util.List;

@Value
public class AccountDto {
    String email;

    String firstName;

    String lastName;

    String country;

    String city;

    String amountOfMoney;
}
