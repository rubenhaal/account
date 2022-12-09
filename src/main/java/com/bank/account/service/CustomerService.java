package com.bank.account.service;

import com.bank.account.dto.CustomerDto;
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
    public CustomerDto getCustomer(long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);

        if (customerOpt.isPresent()) {
            return mapper.map(customerOpt.get(), CustomerDto.class);
        }else {
            return null;
        }
    }
}
