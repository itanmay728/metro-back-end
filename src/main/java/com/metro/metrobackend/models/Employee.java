package com.metro.metrobackend.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "employees", uniqueConstraints = {
	@UniqueConstraint(columnNames = "empCode"),
	@UniqueConstraint(columnNames = "user_id")
})
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullName;
	
	private LocalDate dateOfBirth;
	
	private String gender;
	
	private String nationality;
	
	private String address;
	
	private String phoneNumber;
	
	private LocalDate hireDate = LocalDate.now();
	
	@CreationTimestamp
	private LocalDateTime createdAt; 
	
	private String aadharNumber;
	
	private String department;
	
	private boolean approved = false;
	
	private Long approvedByEmpCode;
	
	@Column(nullable = false, unique = true)
	private Long empCode;
	
	private Long managerEmpCode;

	

	/* ✅ Owning side */
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;



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



	public LocalDate getHireDate() {
		return hireDate;
	}



	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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



	public boolean isApproved() {
		return approved;
	}



	public void setApproved(boolean approved) {
		this.approved = approved;
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Employee(Long id, String fullName, LocalDate dateOfBirth, String gender, String nationality, String address,
			String phoneNumber, LocalDate hireDate, LocalDateTime createdAt, String aadharNumber, String department,
			boolean approved, Long approvedByEmpCode, Long empCode, Long managerEmpCode, User user) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.nationality = nationality;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.createdAt = createdAt;
		this.aadharNumber = aadharNumber;
		this.department = department;
		this.approved = approved;
		this.approvedByEmpCode = approvedByEmpCode;
		this.empCode = empCode;
		this.managerEmpCode = managerEmpCode;
		this.user = user;
	}



	public Employee() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	
}
