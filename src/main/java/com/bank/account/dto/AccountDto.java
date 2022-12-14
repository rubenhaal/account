package com.bank.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AccountDto {

    private Long id;
    @NotNull
    private Long userId;
    private long credit;
    private List<TransactionDto> transactions;
}
