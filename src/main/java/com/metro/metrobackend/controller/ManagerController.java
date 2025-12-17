package com.metro.metrobackend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.metrobackend.services.EmployeeService;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

	private final EmployeeService employeeService;
	
	public ManagerController(EmployeeService employeeService) {
		
		this.employeeService = employeeService;
	}
	
	@PostMapping("/addemployee")
	public ResponseEntity<?> addEmployee(@RequestBody Map<String, String> employeeData){
		
		System.out.println(employeeData);
		return ResponseEntity.ok(employeeService.saveNewEmployee(employeeData));
		
	}
}
