package com.newt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newt.model.Customer;
import com.newt.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	 @Autowired
	 private CustomerRepository customerRepository;


	@Override
	public Customer findCustomerBycustomerId(int customerId) {
		return customerRepository.findCustomerBycustomerId(customerId);
	}


	@Override
	public Customer findCustomerBycustomerName(String customerName) {
		return customerRepository.findCustomerBycustomerName(customerName);
	}


	@Override
	public Iterable<Customer> findAll() {
		return  customerRepository.findAll();
	}


	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}


	@Override
	public Customer findOne(int id) {
		return customerRepository.findOne(id);
	}


	@Override
	public Customer findCustomerBycustomerEmail(String customerEmail) {
		return customerRepository.findCustomerBycustomerEmail(customerEmail);
	}


	@Override
	public Customer findCustomerByusername(String username) {
		return customerRepository.findCustomerByusername(username);
	}
	

}
