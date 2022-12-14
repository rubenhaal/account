package com.bank.account.service;

import com.bank.account.dto.TransactionDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.model.entity.AccountEntity;
import com.bank.account.model.entity.Transaction;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    public void whenCreateTransaction_ThenReturnTransaction() throws AccountNotFoundException {
        //Given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(100L);
        transactionDto.setAccountId(20L);

        Transaction transaction = new Transaction();
        transaction.setAmount(100L);

        AccountEntity account = new AccountEntity();
        account.setTransactions(new ArrayList<>());
        account.setCredit(0L);

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        when(accountRepository.findById(20L)).thenReturn(Optional.of(account));
        //When
        TransactionDto result = transactionService.createTransaction(transactionDto);
        //Then
        assertThat(result.getAmount()).isEqualTo(100L);
    }

    @Test
    public void WhenCreateTransactionWithoutValidAccountLinked_ThenExceptionIsThrown(){
        TransactionDto transactionDto= new TransactionDto();
        transactionDto.setAccountId(20L);
        when(accountRepository.findById(20L)).thenReturn(Optional.empty());
        assertThatExceptionOfType(AccountNotFoundException.class)
                .isThrownBy(() -> transactionService.createTransaction(transactionDto));
    }

}