package com.bank.account.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Customer customer;
    private Long credit;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
