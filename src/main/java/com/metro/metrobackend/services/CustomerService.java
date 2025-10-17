package com.metro.metrobackend.services;

import org.springframework.http.ResponseEntity;

import com.metro.metrobackend.models.Customer;

public interface CustomerService {
	
	ResponseEntity<?> saveNewCustomers(Customer customers); 

}
