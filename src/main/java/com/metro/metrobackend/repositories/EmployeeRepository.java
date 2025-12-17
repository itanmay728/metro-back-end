package com.metro.metrobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.metrobackend.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
