package com.metro.metrobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.services.impl.CustomerServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@PostMapping("/addcustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customers) {
		
		ResponseEntity result = customerServiceImpl.saveNewCustomers(customers);
		
		return result;
		
	}
}
