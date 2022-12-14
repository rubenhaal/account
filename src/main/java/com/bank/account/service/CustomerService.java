package com.bank.account.service;

import com.bank.account.dto.*;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.model.entity.Customer;
import com.bank.account.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper mapper;

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto){

        Customer customer = mapper.map(customerDto, Customer.class);

        customer = customerRepository.save(customer);

        return mapper.map(customer, CustomerDto.class);
    }
    @Transactional
    public CustomerDto getCustomer(long id) throws CustomerNotFoundException {
        Optional<Customer> customerOpt = customerRepository.findById(id);

        if (!customerOpt.isPresent()) {
            throw new CustomerNotFoundException();
        }
            return mapper.map(customerOpt.get(), CustomerDto.class);
    }

    @Transactional
    public CustomerDataDto getCustomerData(long id) throws CustomerNotFoundException {
        Optional<Customer> customerOpt = customerRepository.findById(id);

        if (!customerOpt.isPresent()) {
            throw new CustomerNotFoundException();
        }
        return mapToCustomerDataDto(customerOpt.get());
    }

    private CustomerDataDto mapToCustomerDataDto( Customer customer){
        CustomerDataDto customerDataDto = new CustomerDataDto();
        customerDataDto.setCustomerDto( mapper.map(customer, CustomerDto.class));
        if(customer.getAccounts()!=null){
            customerDataDto.setAccountDto(customer.getAccounts().stream()
                    .map(accountData -> mapper.map(accountData, AccountDto.class))
                                            .toList());
        }
        return  customerDataDto;
    }


}
