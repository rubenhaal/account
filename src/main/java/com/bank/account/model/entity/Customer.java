package com.bank.account.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Size(min = 2, message = "User name should have at least 2 characters")
    private String name;
    @NotNull
    @Size(min = 2, message = "User surname should have at least 2 characters")
    private String surname;

    @OneToMany
    private List<AccountEntity> accounts;


}
