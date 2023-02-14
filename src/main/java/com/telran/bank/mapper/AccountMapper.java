package com.telran.bank.mapper;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto accountEntityToDto(Account account);

    Account accountDtoToEntity(AccountDto accountDTO);

    List<AccountDto> accountsEntityToDto(List<Account> accounts);
}
