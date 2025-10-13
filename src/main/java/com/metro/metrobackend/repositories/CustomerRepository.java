package com.metro.metrobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metro.metrobackend.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
