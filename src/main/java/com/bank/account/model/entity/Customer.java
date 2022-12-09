package com.bank.account.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    private Long id;

    private String name;
    private String surname;


}
