package com.metro.metrobackend.services;

import com.metro.metrobackend.dto.EmployeeCreateRequest;
import com.metro.metrobackend.models.Employee;
import com.metro.metrobackend.models.Role;
import com.metro.metrobackend.models.RoleName;

public interface AdminUserService {

	Employee createOrUpgradeEmployee(EmployeeCreateRequest req);
	
}
