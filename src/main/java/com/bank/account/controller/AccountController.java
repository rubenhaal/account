package com.bank.account.controller;

import com.bank.account.dto.AccountDto;
import com.bank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/createaccount")
    public AccountDto createAccount(@RequestBody AccountDto account){

        accountService.createAccount(account);
        return new AccountDto();
    }
}
