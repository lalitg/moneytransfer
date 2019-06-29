package com.revolut.repository;

import com.revolut.model.Account;

public interface AccountRepository {
	Account get(Long id);
    long save(Account acc);
}