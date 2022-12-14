package com.bank.account.controller;

import com.bank.account.dto.CustomerDataDto;
import com.bank.account.dto.CustomerDto;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto showCustomer(@PathVariable Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/data/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDataDto showCustomerInformation(@PathVariable Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomerData(customerId);
    }
}
