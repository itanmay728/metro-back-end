package com.metro.metrobackend.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.metro.metrobackend.models.*;
import com.metro.metrobackend.repositories.EmployeeRepository;
import com.metro.metrobackend.repositories.RoleRepository;
import com.metro.metrobackend.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
	
    @Bean
    public CommandLineRunner initRolesAndAdmin(
            RoleRepository roleRepo,
            UserRepository userRepo,
            PasswordEncoder encoder,
            EmployeeRepository employeeRepository
           
    ) {
        return args -> {

            // 1️⃣ Insert Roles If Not Exist
            for (RoleName role : RoleName.values()) {
                if (roleRepo.findByName(role).isEmpty()) {
                	Role r = new Role();
                	r.setName(role);
                    roleRepo.save(r);
                }
            }

            // 2️⃣ Create Default Admin If Not Exists
            String adminEmail = "admin@metro.com";

            if (!userRepo.existsByEmail(adminEmail)) {
                User admin = new User(adminEmail, encoder.encode("Adm1in@123"));

                Role adminRole = roleRepo.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("ADMIN role missing"));

                admin.setRoles(Set.of(adminRole));              
                
                Employee employee = new Employee();
                employee.setFullName("Tanmay");
                
                DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dob = LocalDate.parse("11/02/2000", formatter);
                employee.setDateOfBirth(dob);              
                                                          
                employee.setGender("Male");
                employee.setNationality("Indian");
                employee.setAddress("New Delhi Dwarka sec-3");
                employee.setPhoneNumber("6202907277");
                employee.setAadharNumber("34567890876");
                employee.setDepartment("admin");
                employee.setApproved(true);
                employee.setApprovedByEmpCode(101L);
                employee.setEmpCode(101L);
                
                employee.setUser(admin);
                admin.setEmployee(employee);
                
                userRepo.save(admin);
                employeeRepository.save(employee);
                
                System.out.println("✔ Default admin employee record created");
                
                System.out.println("✔ Default Admin Created");
            } else {
                System.out.println("✔ Default Admin Already Exists");
            }

            System.out.println("✔ Roles Initialized");
        };
    }
}
