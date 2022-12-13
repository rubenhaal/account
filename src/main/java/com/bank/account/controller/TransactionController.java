package com.bank.account.controller;

import com.bank.account.dto.TransactionDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("createTransaction")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@RequestBody TransactionDto transaction ) throws AccountNotFoundException {
        return transactionService.createTransaction(transaction);
    }
}
