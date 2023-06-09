package com.snw.dao;

import java.util.List;

import com.snw.entity.Customer;

public interface CustomerDAO {

	public List<Customer> get();

	public void save(Customer theCustomer);

	public Customer get(int theId);

	public void delete(int theId);
	
}
