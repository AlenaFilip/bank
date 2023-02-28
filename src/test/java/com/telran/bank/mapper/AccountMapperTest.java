package com.telran.bank.mapper;

import com.telran.bank.dto.AccountDto;
import com.telran.bank.entity.Account;
import com.telran.bank.util.DtoCreator;
import com.telran.bank.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for AccountMapper")
@ExtendWith(MockitoExtension.class)
class AccountMapperTest {

    private final AccountMapper mapper = new AccountMapperImpl();

    @DisplayName("Test mapping Account to AccountDto")
    @Test
    void accountEntityToDtoTest() {
        Account account = EntityCreator.getAccount();
        AccountDto expectedDto = DtoCreator.getAccountDTO();
        AccountDto actualDto = mapper.accountEntityToDto(account);
        assertEquals(expectedDto.toString(), actualDto.toString());
    }

    @DisplayName("Test mapping AccountDto to Account")
    @Test
    void accountDtoToEntityTest() {
        AccountDto accountDto = DtoCreator.getAccountDTO();
        Account expectedEntity = EntityCreator.getAccount();
        Account actualEntity = mapper.accountDtoToEntity(accountDto);
        assertEquals(expectedEntity.toString(), actualEntity.toString());
    }

    @DisplayName("Test mapping List Account to List AccontDto")
    @Test
    void accountsEntityToDtoTest() {
        Account account = EntityCreator.getAccount();
        List<Account> accounts = List.of(account);
        AccountDto accountDto = DtoCreator.getAccountDTO();
        List<AccountDto> expectedList = List.of(accountDto);
        List<AccountDto> actualList = mapper.accountsEntityToDto(accounts);
        assertEquals(expectedList.get(0).toString(), actualList.get(0).toString());
    }
}
