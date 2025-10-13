package com.metro.metrobackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.repositories.CustomerRepository;
import com.metro.metrobackend.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public String saveNewCustomers(Customer customers) {
		customerRepository.save(customers);
		return "customer saved successfully ";
	}

}
