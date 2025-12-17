package com.metro.metrobackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.metrobackend.models.Role;
import com.metro.metrobackend.models.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(RoleName name);

}
