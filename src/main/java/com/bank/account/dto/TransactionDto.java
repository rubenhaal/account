package com.bank.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {
    @NotNull
    private Long amount;
}
