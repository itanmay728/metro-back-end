package com.metro.metrobackend.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeCreateRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullName;
	
	private LocalDate dateOfBirth;
	
	private String gender;
	
	private String nationality;
	
	private String address;
	
	private String phoneNumber;
	
	private String email;
	
	private String aadharNumber;
	
	private String department;
	
	private boolean approve = false;
	
	private Long approvedByEmpCode;
	
	@Column(nullable = false, unique = true)
	private Long empCode;
	
	private Long managerEmpCode;

	@Column(nullable = false)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmployeeCreateRequest(Long id, String fullName, LocalDate dateOfBirth, String gender, String nationality,
			String address, String phoneNumber, String email, String aadharNumber, String department, boolean approve,
			Long approvedByEmpCode, Long empCode, Long managerEmpCode, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.nationality = nationality;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.aadharNumber = aadharNumber;
		this.department = department;
		this.approve = approve;
		this.approvedByEmpCode = approvedByEmpCode;
		this.empCode = empCode;
		this.managerEmpCode = managerEmpCode;
		this.password = password;
	}

	public EmployeeCreateRequest() {
		
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
}
