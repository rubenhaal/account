package com.bank.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    @NotNull
    private Long amount;
    @NotNull
    private Long accountId;
}
