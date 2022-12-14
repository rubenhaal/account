package com.bank.account.service;

import com.bank.account.dto.TransactionDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.model.entity.AccountEntity;
import com.bank.account.model.entity.Transaction;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto) throws AccountNotFoundException {

        Optional<AccountEntity> accountOpt = accountRepository.findById(transactionDto.getAccountId());

        if(accountOpt.isEmpty()){
            throw new AccountNotFoundException();
        }
        AccountEntity account = accountOpt.get();

        Transaction transaction = mapper.map(transactionDto, Transaction.class);
        transactionRepository.save(transaction);
        account.getTransactions().add(transaction);
        account.setCredit(account.getCredit()+ transaction.getAmount());
        transactionRepository.save(transaction);


        return mapper.map(transaction, TransactionDto.class);
    }
}
