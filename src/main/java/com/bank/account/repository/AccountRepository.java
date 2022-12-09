package com.bank.account.repository;

import com.bank.account.model.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
