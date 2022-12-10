package com.bank.account.service;

import com.bank.account.dto.AccountDto;
import com.bank.account.model.entity.AccountEntity;
import com.bank.account.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper mapper;

    public AccountDto createAccount( AccountDto accountDto){

        AccountEntity account= mapper.map(accountDto, AccountEntity.class);
        account = accountRepository.save(account);
        return mapper.map(account, AccountDto.class);
    }

}
