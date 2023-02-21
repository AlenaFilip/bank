package com.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

@Value
public class AccountDto {

    String firstName;

    String lastName;

    String email;

    String country;

    String city;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    String creationDate;

    String amountOfMoney;
}
