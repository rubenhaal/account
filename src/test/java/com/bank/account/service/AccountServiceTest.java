package com.bank.account.service;

import com.bank.account.dto.AccountDto;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.exception.GeneralAccException;
import com.bank.account.model.entity.AccountEntity;
import com.bank.account.model.entity.Customer;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private ModelMapper modelMapper;

    @Before
    public void setUp(){

    }

    @Test
    public void createAccount() throws GeneralAccException {
        //Given
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(20L);
        accountDto.setCredit(2000L);

        AccountEntity account = new AccountEntity();
        account.setCredit(2000L);

        Customer customer = new Customer();
        customer.setId(20L);
        account.setCustomer(customer);

        when(customerRepository.findById(20L)).thenReturn(Optional.of(customer));
        when(accountRepository.save( any())).thenReturn(account);
        //when
        AccountDto result = accountService.createAccount(accountDto);

        //then
        assertThat(result.getCredit()).isEqualTo(2000L);
        assertThat(result.getUserId()).isEqualTo(20L);
        verify(accountRepository, times(1)).save(any());
        verify(customerRepository, times(1)).findById(20L);

    }

    @Test
    public void whenCreateAccountWithInvalidCustomer_ThenThrowCustomerNotFoundException() throws GeneralAccException {
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(20L);
        accountDto.setCredit(2000L);
        when(customerRepository.findById(20L)).thenReturn(Optional.empty());
        assertThatExceptionOfType(CustomerNotFoundException.class).isThrownBy(() -> accountService.createAccount(accountDto));

    }

    @Test
    public void whenCreateAccount_ThenShowTheActualCreditFirstTransaction(){

    }

}