package com.telran.bank.dto;

import lombok.Value;

@Value
public class AccountDto {

    String firstName;

    String lastName;

    String email;

    String country;

    String city;

    String creationDate;

    String amountOfMoney;
}
