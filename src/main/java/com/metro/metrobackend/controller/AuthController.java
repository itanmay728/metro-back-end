package com.metro.metrobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.metrobackend.dto.AuthResponse;
import com.metro.metrobackend.dto.LoginRequest;
import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.models.Employee;
import com.metro.metrobackend.models.User;
import com.metro.metrobackend.repositories.UserRepository;
import com.metro.metrobackend.repositories.RoleRepository;
import com.metro.metrobackend.security.JwtTokenUtil;
import com.metro.metrobackend.services.CustomerService;
import com.metro.metrobackend.services.EmployeeService;
import com.metro.metrobackend.services.impl.EmployeeServiceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;
    private final EmployeeService employeeService;
    
    
  	private final CustomerService customerService;

    public AuthController(AuthenticationManager authManager,
                          JwtTokenUtil jwtTokenUtil,
                          UserRepository userRepo,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepo, EmployeeService employeeService, CustomerService customerService) {
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepo.findByEmail(request.getEmail()).orElseThrow();

        var roles = user.getRoles().stream()
                .map(r -> r.getName().name())
                .collect(Collectors.toSet());
        String password = user.getPassword();
        System.out.println(password);
        System.out.println(roles);

        String token = jwtTokenUtil.generateToken(user.getEmail(), roles);
        
        Employee employee =  user.getEmployee();
        
        Customer customer = user.getCustomer();

        return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), password, roles, employee, customer));
    }
    
  

	@PostMapping("/addcustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Map<String, String> customers) {
		
		ResponseEntity result = customerService.saveNewCustomers(customers);
		System.out.println(customers);
		
		return result;
		
	}
}
