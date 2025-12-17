package com.metro.metrobackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.metrobackend.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
