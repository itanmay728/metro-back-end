package com.metro.metrobackend.dto;

import java.util.Set;

import com.metro.metrobackend.models.Employee;

public class UpdatedEmployeeResponse {

	
	private String email;
	private Set<String> roles;
	private Employee employee;

	public UpdatedEmployeeResponse( String email, Set<String> roles, Employee employee) {
		
		this.email = email;
		this.roles = roles;
		this.employee = employee;
		
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmail() {
		return email;
	}

	public Set<String> getRoles() {
		return roles;
	}
}
