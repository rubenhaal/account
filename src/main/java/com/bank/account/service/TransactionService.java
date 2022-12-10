package com.bank.account.service;

import com.bank.account.dto.TransactionDto;
import com.bank.account.model.entity.Transaction;
import com.bank.account.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionDto createTransaction(TransactionDto transactionDto){

        Transaction transaction = mapper.map(transactionDto, Transaction.class);

        transaction = transactionRepository.save(transaction);

        return mapper.map(transaction, TransactionDto.class);
    }
}
