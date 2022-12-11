package com.bank.account.service;

import com.bank.account.dto.AccountData;
import com.bank.account.dto.AccountDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.exception.GeneralAccException;
import com.bank.account.model.entity.AccountEntity;
import com.bank.account.model.entity.Customer;
import com.bank.account.model.entity.Transaction;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
        assertThat(result.getCredit()).isEqualTo(2000L);
        verify(accountRepository, times(1)).save(any());
        verify(customerRepository, times(1)).findById(20L);

    }

    @Test
    public void whenCreateAccountWithInvalidCustomer_ThenThrowCustomerNotFoundException() {
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(20L);
        accountDto.setCredit(2000L);
        when(customerRepository.findById(20L)).thenReturn(Optional.empty());
        assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> accountService.createAccount(accountDto));

    }

    @Test
    public void whenGetAccountData_ThenRetrieveAccountData() throws GeneralAccException {
        //Given
        AccountEntity account = new AccountEntity();
        account.setCredit(2000L);
        Transaction transaction = new Transaction();
        transaction.setAmount(2000L);
        account.setTransactions(List.of(transaction));

        Customer customer = new Customer();
        customer.setName("nameTest");
        customer.setSurname("SurnameTest");
        customer.setId(20L);
        customer.setAccounts(List.of(account));
        account.setCustomer(customer);


        when( accountRepository.findById(20L)).thenReturn(Optional.of(account));
        //When
        AccountData accountData = accountService.getAccountData(20L);
        //then
        assertThat(accountData.getAccountDto()).isNotNull();
        assertThat(accountData.getAccountDto().getCredit()).isEqualTo(2000L);
        assertThat(accountData.getAccountDto().getUserId()).isEqualTo(20L);
        assertThat(accountData.getCustomerDto()).isNotNull();
        assertThat(accountData.getCustomerDto().getSurname()).isEqualTo("SurnameTest");
        assertThat(accountData.getCustomerDto().getName()).isEqualTo("nameTest");
        assertThat(accountData.getCustomerDto().getId()).isEqualTo(20L);
        assertThat(accountData.getTransactionDtos()).isNotNull();
        assertThat(accountData.getTransactionDtos()).isNotEmpty();
        assertThat(accountData.getTransactionDtos().get(0)).isNotNull();
        assertThat(accountData.getTransactionDtos().get(0).getAmount()).isEqualTo(2000L);

    }

    @Test
    public void whenGetAccountDataWithInvalidAccountId_ThenThrowAccountNotFoundException() {

        when(accountRepository.findById(20L)).thenReturn(Optional.empty());
        assertThatExceptionOfType(AccountNotFoundException.class)
                .isThrownBy(() -> accountService.getAccountData(20L));

    }
}