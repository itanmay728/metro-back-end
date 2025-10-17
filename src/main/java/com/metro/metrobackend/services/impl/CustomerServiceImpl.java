package com.metro.metrobackend.services.impl;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.repositories.CustomerRepository;
import com.metro.metrobackend.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public ResponseEntity<?> saveNewCustomers(Customer customers) {
		
		try {
			customerRepository.save(customers);
			return ResponseEntity.ok("customer saved successfully");
		} catch (Exception e) {
			 return ResponseEntity
		                .status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(java.util.Map.of("error", "Something went wrong: " + e.getMessage()));
		}
		
		
	}

}
