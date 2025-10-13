package com.metro.metrobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.services.impl.CustomerServiceImpl;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@PostMapping("/addcustomer")
	public String addCustomer(@RequestBody Customer customers) {
		
		String result = customerServiceImpl.saveNewCustomers(customers);
		
		return result;
		
	}
}
