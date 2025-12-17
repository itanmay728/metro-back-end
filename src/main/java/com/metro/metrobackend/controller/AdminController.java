package com.metro.metrobackend.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.metrobackend.dto.AllEmployeeDataRequest;
import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.models.Employee;
import com.metro.metrobackend.repositories.CustomerRepository;
import com.metro.metrobackend.repositories.EmployeeRepository;
import com.metro.metrobackend.repositories.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	private final CustomerRepository customerRepository;

	public AdminController(EmployeeRepository employeeRepository, UserRepository userRepository,
			CustomerRepository customerRepository) {
		this.employeeRepository = employeeRepository;
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
	}

	@GetMapping("/allemployees")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllEmployee() {

		List<Employee> AllEmployees = employeeRepository.findAll();

		List<AllEmployeeDataRequest> allEmployeeDataRequests = AllEmployees.stream().map(emp -> {

			Set<String> roleNames = emp.getUser().getRoles().stream().map(r -> r.getName().name()) // ROLE_ADMIN,
																									// ROLE_MANAGER,
																									// etc.
					.collect(Collectors.toSet());

			return new AllEmployeeDataRequest(emp.getFullName(), emp.getDateOfBirth(), emp.getGender(),
					emp.getNationality(), emp.getAddress(), emp.getPhoneNumber(), emp.getHireDate(),
					emp.getAadharNumber(), emp.getDepartment(), emp.getApprovedByEmpCode(), emp.getEmpCode(),
					emp.getManagerEmpCode(), emp.getUser().getEmail(), roleNames);
		}).toList();
		return ResponseEntity.ok(allEmployeeDataRequests);

	}

	@GetMapping("/allcustomer")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Customer> getAllCustomer() {

		return customerRepository.findAll();

	}

}
