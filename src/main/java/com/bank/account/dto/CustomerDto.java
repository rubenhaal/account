package com.bank.account.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    private Long id;
    @NotEmpty
    @Size(min = 2, message = "User name should have at least 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 2, message = "User surname should have at least 2 characters")
    private String surname;
}
