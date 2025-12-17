package com.metro.metrobackend.dto;

public class EmployeeCreateRequest {

	private String fullName;
	private String empCode;
	private String department;
	private String email;
	private String password; // for new user or temporary one
	private String role; // "MANAGER" / "COUNTER_EXECUTIVE"

	public String getFullName() {
		return fullName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public String getDepartment() {
		return department;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
