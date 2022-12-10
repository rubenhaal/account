package com.bank.account.repository;

import com.bank.account.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository  extends CrudRepository<Transaction, Long> {
}
