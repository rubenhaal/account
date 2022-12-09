package com.bank.account.service;

import com.bank.account.dto.AccountDto;
import static org.assertj.core.api.Assertions.*;

import com.bank.account.model.entity.AccountEntity;
import com.bank.account.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;
    @Spy
    ModelMapper modelMapper;

    @Before
    public void setUp(){

    }

    @Test
    public void createAccount(){
        //Given
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId("testId");
        accountDto.setCredit(2000L);

        AccountEntity account = new AccountEntity();
        account.setUserId("testId");
        account.setCredit(2000L);

        when(accountRepository.save( any())).thenReturn(account);
        //when
        AccountDto result = accountService.createAccount(accountDto);

        //then
        assertThat(result.getCredit()).isEqualTo(2000L);
        assertThat(result.getUserId()).isEqualTo("testId");

    }

    //I'm not sure
    @Test
    public void whenCreateAccount_ThenThrowExceptionIfUserAlreadyExist(){

    }
    @Test
    public void whenCreateAccount_ThenShowTheActualCreditFirstTransaction(){

    }
}