package com.metro.metrobackend.services.impl;

import java.util.Set;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.models.Employee;
import com.metro.metrobackend.models.MetroCard;
import com.metro.metrobackend.models.Role;
import com.metro.metrobackend.models.RoleName;
import com.metro.metrobackend.models.User;
import com.metro.metrobackend.repositories.CustomerRepository;
import com.metro.metrobackend.repositories.MetroCardRepository;
import com.metro.metrobackend.repositories.RoleRepository;
import com.metro.metrobackend.repositories.UserRepository;
import com.metro.metrobackend.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final SecureRandom random = new SecureRandom();

	private final CustomerRepository customerRepository;
	
	private final MetroCardRepository metroCardRepository;

	private final PasswordEncoder passwordEncoder;

	private final RoleRepository roleRepository;

	private final UserRepository userRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository, UserRepository userRepository, MetroCardRepository metroCardRepository) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.metroCardRepository = metroCardRepository;
	}

	public static String generateMetroCardNumber() {
		StringBuilder cardNumber = new StringBuilder();

		// First digit non-zero
		cardNumber.append(random.nextInt(9) + 1);

		// Remaining 15 digits
		for (int i = 0; i < 11; i++) {
			cardNumber.append(random.nextInt(10));
		}

		return cardNumber.toString();
	}

	@Override
	public void createMetroCard(Customer customer) {
		// TODO Auto-generated method stub
		
		String cardNumber = generateMetroCardNumber();
		
		MetroCard metroCard = new MetroCard();
		
		metroCard.setBalance((double) 0);
		metroCard.setCardNumber(cardNumber);
		metroCard.setStatus("Active");
		metroCard.setCustomer(customer);
		
		metroCardRepository.save(metroCard);
	}

	@Override
	public ResponseEntity<?> saveNewCustomers(Map<String, String> customers) {

		try {
			// Create NEW USER
			User user = new User();
			user.setEmail(customers.get("emailId"));

			String encodedPass = passwordEncoder.encode(customers.get("password"));
			user.setPassword(encodedPass);

			// Set CUSTOMER ROLE
			Role role = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException("CUSTOMER role missing"));
			user.setRoles(Set.of(role));

			// Create CUSTOMER entity
			Customer customer = new Customer();
			customer.setFullName(customers.get("fullName"));
			customer.setEmailId(customers.get("emailId"));
			customer.setPhoneNumber(customers.get("phoneNumber"));
			customer.setAddress(customers.get("address"));
			customer.setAadharNumber(customers.get("aadharNumber"));
			customer.setPassword(encodedPass); // store encoded (optional)

			// --- IMPORTANT: set both sides of OneToOne relationship ---
			customer.setUser(user);
			
			
			
			user.setCustomer(customer);

			// 🚀 Save only USER (will automatically save CUSTOMER)
			userRepository.save(user);
			createMetroCard(customer);

			return ResponseEntity.ok("Customer saved successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
		}
	}

	@Override
	public ResponseEntity<?> getAllCardDetails(String email) {
		// TODO Auto-generated method stub
		return null;
	}



	

	

}
