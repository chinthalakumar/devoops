package com.newt.service;

import com.newt.model.Customer;

public interface CustomerService {
	
	public Customer findCustomerBycustomerId(int customerId);
	
    public Customer findCustomerBycustomerName(String customerName);

	public Iterable<Customer>  findAll();

	public Customer save(Customer customer);

	public Customer findOne(int id);

	public Customer findCustomerBycustomerEmail(String customerEmail);

	public Customer findCustomerByusername(String username);

}
