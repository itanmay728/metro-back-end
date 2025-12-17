package com.metro.metrobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.metrobackend.models.EmployeeCreateRequest;

public interface EmployeeCreateRequestRepository extends JpaRepository<EmployeeCreateRequest, Long> {

}
