package com.metro.metrobackend.services.impl;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.metro.metrobackend.dto.AuthResponse;
import com.metro.metrobackend.dto.UpdatedEmployeeResponse;
import com.metro.metrobackend.models.Employee;
import com.metro.metrobackend.models.Role;
import com.metro.metrobackend.models.RoleName;
import com.metro.metrobackend.models.User;
import com.metro.metrobackend.repositories.EmployeeRepository;
import com.metro.metrobackend.repositories.RoleRepository;
import com.metro.metrobackend.repositories.UserRepository;
import com.metro.metrobackend.services.EmployeeService;
import java.util.Random;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final UserRepository userRepository;
	private final EmployeeRepository employeeRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public EmployeeServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.employeeRepository = employeeRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Employee getLoggedInEmployeeDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !auth.isAuthenticated()) {
			throw new RuntimeException("User not authenticated");
		}

		String email = auth.getName();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Logged-in user not found: " + email));

		Employee employee = user.getEmployee();
		System.out.println(employee);
		return employee;
	}

	@Override
	public UpdatedEmployeeResponse updateEmployeeData(String fullName, String dateOfBirth, String gender,
			String Nationality, String address, String phoneNumber, String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Logged-in user not found: " + email));

		Employee employee = user.getEmployee();
		LocalDate dateOfBirth1 = LocalDate.parse(dateOfBirth);
		employee.setFullName(fullName);
		employee.setDateOfBirth(dateOfBirth1);
		employee.setGender(gender);
		employee.setNationality(Nationality);
		employee.setAddress(address);
		employee.setPhoneNumber(phoneNumber);

		employeeRepository.save(employee);
		Employee employee1 = user.getEmployee();

		var roles = user.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet());

		UpdatedEmployeeResponse updatedEmployeeResponse = new UpdatedEmployeeResponse(email, roles, employee1);

		return updatedEmployeeResponse;
	}

	public static int generateEmpCode() {
		Random random = new Random();
		return 102 + random.nextInt(9000); // 102 to 9101
	}

	@Override
	public ResponseEntity<?> saveNewEmployee(Map<String, String> employeeData) {

		try {

			User user = new User();
			user.setEmail(employeeData.get("email"));

//			RoleName roleName = (RoleName) employeeData.get("roles");

			RoleName roleName = RoleName.valueOf(employeeData.get("roles"));
			Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("role missing"));

			user.setRoles(Set.of(role));

			String encodedPass = passwordEncoder.encode(employeeData.get("password"));

			user.setPassword(encodedPass);

			Employee employee = new Employee();

			employee.setFullName(employeeData.get("fullName"));
			employee.setPhoneNumber(employeeData.get("phoneNumber"));
			employee.setGender(employeeData.get("gender"));
			employee.setDepartment(employeeData.get("department"));
			employee.setAadharNumber(employeeData.get("aadharNumber"));
			
			Long empCode = (long) generateEmpCode();
			employee.setEmpCode(empCode);

			LocalDate dateOfBirth1 = LocalDate.parse(employeeData.get("dateOfBirth"));
			employee.setDateOfBirth(dateOfBirth1);

			employee.setNationality(employeeData.get("nationality"));
			employee.setAddress(employeeData.get("address"));

			employee.setUser(user);
			user.setEmployee(employee);

			userRepository.save(user);

			return ResponseEntity.ok("Customer saved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
		}

	}

}
