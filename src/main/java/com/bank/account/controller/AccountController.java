package com.bank.account.controller;

import com.bank.account.dto.AccountDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("accounts")
public class AccountController {

    @PostMapping("/createaccount")
    public AccountDto createAccount(){
        return new AccountDto();
    }
}
