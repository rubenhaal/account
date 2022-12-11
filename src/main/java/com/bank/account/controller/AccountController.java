package com.bank.account.controller;

import com.bank.account.dto.AccountDto;
import com.bank.account.exception.GeneralAccException;
import com.bank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/createaccount")
    public AccountDto createAccount(@RequestBody AccountDto account) throws GeneralAccException {

        accountService.createAccount(account);
        return new AccountDto();
    }
}
