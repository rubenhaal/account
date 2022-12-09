package com.bank.account.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AccountEntity {
    @Id
    private Long id;

    private String userId;
    private Long credit;
}
