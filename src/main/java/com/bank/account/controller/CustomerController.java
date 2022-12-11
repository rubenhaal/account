package com.bank.account.controller;

import com.bank.account.dto.CustomerDto;
import com.bank.account.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/{id}")
    public CustomerDto showCustomerInformation(@PathVariable Long id){
        return customerService.getCustomer(id);
    }
}
