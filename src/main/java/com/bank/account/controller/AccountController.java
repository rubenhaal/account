package com.bank.account.controller;

import com.bank.account.dto.AccountData;
import com.bank.account.dto.AccountDto;
import com.bank.account.exception.GeneralAccException;
import com.bank.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/createaccount")
    public AccountDto createAccount(@Valid @RequestBody AccountDto account) throws GeneralAccException {

        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public AccountData getAccountData(@PathVariable Long id) throws GeneralAccException {
        return accountService.getAccountData(id);
    }
}
