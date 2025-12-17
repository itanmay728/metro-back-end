package com.metro.metrobackend.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.metro.metrobackend.models.Customer;

public interface CustomerService {
	
	ResponseEntity<?> saveNewCustomers(Map<String, String> customers);
	

	void createMetroCard(Customer customer);
	
	ResponseEntity<?> getAllCardDetails(String email);
}
