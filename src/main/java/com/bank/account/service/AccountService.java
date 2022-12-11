package com.bank.account.service;

import com.bank.account.dto.AccountDto;
import com.bank.account.exception.GeneralAccException;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.model.entity.AccountEntity;
import com.bank.account.model.entity.Customer;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    public AccountDto createAccount( AccountDto accountDto) throws GeneralAccException {

        Optional<Customer> customerOpt = getCustomer(accountDto);
        AccountEntity account= mapper.map(accountDto, AccountEntity.class);
        account.setCustomer(customerOpt.get());
        account = accountRepository.save(account);

        accountDto= mapper.map(account, AccountDto.class);
        accountDto.setUserId(account.getCustomer().getId());
        return accountDto;
    }

    private Optional<Customer> getCustomer(AccountDto accountDto) throws CustomerNotFoundException {
        Optional <Customer> customerOpt = customerRepository.findById(accountDto.getUserId());
        if(customerOpt.isEmpty()){
            throw new CustomerNotFoundException();
        }
        return customerOpt;
    }
}
