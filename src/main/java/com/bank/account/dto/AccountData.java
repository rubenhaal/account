package com.bank.account.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountData {
    private CustomerDto customerDto;
    private AccountDto accountDto;
    private List<TransactionDto> transactionDtos;
}
