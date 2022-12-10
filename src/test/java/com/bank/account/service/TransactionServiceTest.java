package com.bank.account.service;

import com.bank.account.dto.TransactionDto;
import com.bank.account.model.entity.Transaction;
import com.bank.account.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    public void whenCreateTransaction_ThenReturnTransaction(){
        //Given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(100L);

        Transaction transaction = new Transaction();
        transaction.setAmount(100L);

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        //When
        TransactionDto result = transactionService.createTransaction(transactionDto);
        //Then

        assertThat(result.getAmount()).isEqualTo(100L);
    }

}