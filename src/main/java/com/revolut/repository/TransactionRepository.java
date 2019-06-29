package com.revolut.repository;

import com.revolut.model.MoneyTransaction;

public interface TransactionRepository {
	MoneyTransaction get(Long id);
    long save(MoneyTransaction acc);
}