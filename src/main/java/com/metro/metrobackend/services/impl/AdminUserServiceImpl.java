package com.metro.metrobackend.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.metro.metrobackend.dto.EmployeeCreateRequest;
import com.metro.metrobackend.models.Employee;
import com.metro.metrobackend.models.Role;
import com.metro.metrobackend.models.RoleName;
import com.metro.metrobackend.models.User;
import com.metro.metrobackend.repositories.EmployeeRepository;
import com.metro.metrobackend.repositories.RoleRepository;
import com.metro.metrobackend.repositories.UserRepository;
import com.metro.metrobackend.services.AdminUserService;

import java.util.Set;

public class AdminUserServiceImpl implements AdminUserService{
	
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final EmployeeRepository employeeRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminUserServiceImpl(UserRepository userRepo,
                            RoleRepository roleRepo,
                            EmployeeRepository employeeRepo,
                            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public Employee createOrUpgradeEmployee(EmployeeCreateRequest req) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByEmail(req.getEmail()).orElse(null);

        if (user == null) {
            // 1) New user
            user = new User(req.getEmail(), passwordEncoder.encode(req.getPassword()));
            user.setEnabled(true);

//            Role employeeRole = getOrCreateRole(RoleName.ROLE_COUNTER_EXECUTIVE); // generic employee role if you want
//            user.addRole(employeeRole);
            userRepo.save(user);
        }

        // 2) Add specific role like MANAGER / COUNTER_EXECUTIVE
        RoleName specificRoleName = RoleName.valueOf("ROLE_" + req.getRole().toUpperCase());
//        Role specificRole = getOrCreateRole(specificRoleName);
//        user.addRole(specificRole);
        userRepo.save(user);

        // 3) Create employee profile (if not already exists)
        Employee emp = new Employee();
        return employeeRepo.save(emp);
		
	}
	
	
//	 private Role getOrCreateRole(RoleName name) {
//	        return roleRepo.findByName(name)
//	                .orElseGet(() -> roleRepo.save(new Role(name)));
//	    }

}
