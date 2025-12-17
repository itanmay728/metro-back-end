package com.metro.metrobackend.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.metro.metrobackend.dto.UpdatedEmployeeResponse;
import com.metro.metrobackend.services.EmployeeService;

@RestController
@RequestMapping("/api/allemployees")
public class AllEmployeeController {
	
	private final EmployeeService employeeService;

	public AllEmployeeController(EmployeeService employeeService) {
		
		this.employeeService = employeeService;
		// TODO Auto-generated constructor stub
	}

	@PutMapping("/update")
	public ResponseEntity<?>  updateEmployeeData(@RequestBody Map<String, Object> body) {
		
		String fullName = (String) body.get("fullName");
	    String email = (String) body.get("alternateEmail"); // you send this from frontend
	    String gender = (String) body.get("gender");
	    String nationality = (String) body.get("nationality");
	    String address = (String) body.get("address");
	    String phone = (String) body.get("phoneNumber");
	    String dob = (String) body.get("dateOfBirth"); // coming as "2024-01-05"
	    
		UpdatedEmployeeResponse updatedEmployeeResponse =  employeeService.updateEmployeeData(fullName, dob, gender, nationality, address, phone, email);
		
		return ResponseEntity.ok(updatedEmployeeResponse);

	}
}
