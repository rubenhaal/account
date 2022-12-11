package com.bank.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDto {

    private Long id;
    @NotNull
    private Long userId;
    private Long credit;
}
