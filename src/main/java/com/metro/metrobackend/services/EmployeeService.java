package com.metro.metrobackend.services;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.metro.metrobackend.dto.UpdatedEmployeeResponse;
import com.metro.metrobackend.models.Employee;

public interface EmployeeService {
	
	Employee getLoggedInEmployeeDetails();
	
	UpdatedEmployeeResponse updateEmployeeData(String fullName,  String dateOfBirth,
			 String gender,  String Nationality,  String address,
			 String phoneNumber,  String email);
	
	ResponseEntity<?> saveNewEmployee(Map<String, String> employeeData);

}
