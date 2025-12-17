package com.metro.metrobackend.dto;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;

public class AllEmployeeDataRequest {

	private String fullName;
	
	private LocalDate dateOfBirth;
	
	private String gender;
	
	private String nationality;
	
	private String address;
	
	private String phoneNumber;
	
	private LocalDate hireDate = LocalDate.now();
	
	private String aadharNumber;
	
	private String department;
	
	private Long approvedByEmpCode;
	
	@Column(nullable = false, unique = true)
	private Long empCode;
	
	private Long managerEmpCode;
	
	private String email;
	
	private Set<String> roles;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getApprovedByEmpCode() {
		return approvedByEmpCode;
	}

	public void setApprovedByEmpCode(Long approvedByEmpCode) {
		this.approvedByEmpCode = approvedByEmpCode;
	}

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}

	public Long getManagerEmpCode() {
		return managerEmpCode;
	}

	public void setManagerEmpCode(Long managerEmpCode) {
		this.managerEmpCode = managerEmpCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public AllEmployeeDataRequest(String fullName, LocalDate dateOfBirth, String gender, String nationality,
			String address, String phoneNumber, LocalDate hireDate, String aadharNumber, String department,
			Long approvedByEmpCode, Long empCode, Long managerEmpCode, String email, Set<String> roles) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.nationality = nationality;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.aadharNumber = aadharNumber;
		this.department = department;
		this.approvedByEmpCode = approvedByEmpCode;
		this.empCode = empCode;
		this.managerEmpCode = managerEmpCode;
		this.email = email;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "AllEmployeeDataRequest [fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", nationality=" + nationality + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", hireDate=" + hireDate + ", aadharNumber=" + aadharNumber + ", department=" + department
				+ ", approvedByEmpCode=" + approvedByEmpCode + ", empCode=" + empCode + ", managerEmpCode="
				+ managerEmpCode + ", email=" + email + ", roles=" + roles + "]";
	}

	
	

}
