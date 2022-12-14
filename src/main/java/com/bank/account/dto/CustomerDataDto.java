package com.bank.account.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDataDto {
    private CustomerDto customerDto;
    private List<AccountDto> accountDto;
}
