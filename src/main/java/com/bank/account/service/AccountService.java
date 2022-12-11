package com.bank.account.service;

import com.bank.account.dto.AccountData;
import com.bank.account.dto.AccountDto;
import com.bank.account.dto.CustomerDto;
import com.bank.account.dto.TransactionDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.exception.GeneralAccException;
import com.bank.account.model.entity.AccountEntity;
import com.bank.account.model.entity.Customer;
import com.bank.account.model.entity.Transaction;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public AccountDto createAccount( AccountDto accountDto) throws GeneralAccException {

        Optional<Customer> customerOpt = getCustomer(accountDto);

        AccountEntity account= mapper.map(accountDto, AccountEntity.class);
        account.setCustomer(customerOpt.get());
        account.setTransactions(createTransactions(accountDto));
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
    private List<Transaction> createTransactions(AccountDto accountDto){
        Transaction transaction = new Transaction();
        transaction.setAmount(accountDto.getCredit());
        return List.of(transaction);
    }
    @Transactional
    public AccountData getAccountData(Long id) throws GeneralAccException {
        Optional<AccountEntity> accountOpt = accountRepository.findById(id);
        if(accountOpt.isEmpty()){
            throw new AccountNotFoundException();
        }
        return mapToAccountDataDto(accountOpt.get());
    }

    private AccountData mapToAccountDataDto( AccountEntity account){
        AccountData accountData = new AccountData();
        accountData.setCustomerDto( mapper.map(account.getCustomer(), CustomerDto.class));
        accountData.setAccountDto(mapper.map(account, AccountDto.class));
        accountData.setTransactionDtos(account.getTransactions().stream()
                .map(transaction -> mapper.map(transaction, TransactionDto.class)).toList());
        accountData.getAccountDto().setUserId(account.getCustomer().getId());
        return  accountData;
    }

}
