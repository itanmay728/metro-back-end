package com.metro.metrobackend.dto;

import java.util.Set;

import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.models.Employee;

public class AuthResponse {

	private String token;
	private String email;
	private String password;
	private Set<String> roles;
	private Employee employee;
	private Customer customer;

	public AuthResponse(String token, String email,String password, Set<String> roles, Employee employee, Customer customer) {
		this.token = token;
		this.email = email;
		this.roles = roles;
		this.employee = employee;
		this.password = password;
		this.customer = customer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getToken() {
		return token;
	}

	public String getEmail() {
		return email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
