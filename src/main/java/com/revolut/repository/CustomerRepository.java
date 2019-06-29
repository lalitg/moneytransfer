package com.revolut.repository;

import java.util.List;

import com.revolut.model.Customer;

public interface CustomerRepository {
	public Customer get(Long id);
	public long save(Customer cust);
}