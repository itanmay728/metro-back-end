package com.metro.metrobackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.metrobackend.dto.CardAndCardHistory;
import com.metro.metrobackend.dto.CardHistoryRequest;
import com.metro.metrobackend.dto.RechargeResponse;
import com.metro.metrobackend.models.CardHistory;
import com.metro.metrobackend.models.Customer;
import com.metro.metrobackend.models.MetroCard;
import com.metro.metrobackend.repositories.CardHistoryRepository;
import com.metro.metrobackend.repositories.CustomerRepository;
import com.metro.metrobackend.repositories.MetroCardRepository;
import com.metro.metrobackend.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	private final CustomerRepository customerRepository;
	private final MetroCardRepository metroCardRepository;
	private final CardHistoryRepository cardHistoryRepository;
	
	public CustomerController(CustomerService customerService, CustomerRepository customerRepository, MetroCardRepository metroCardRepository, CardHistoryRepository cardHistoryRepository) {
		this.customerService = customerService;
		this.customerRepository = customerRepository;
		this.metroCardRepository = metroCardRepository;
		this.cardHistoryRepository = cardHistoryRepository;
	}
	
	
	@GetMapping("/allcarddetails")
	public ResponseEntity<?> getCustomerCards(Authentication authentication) {

	    String email = authentication.getName(); // from JWT sub
	    Customer customer = customerRepository
	            .findByEmailId(email)
	            .orElseThrow();

	    List<MetroCard> cards = customer.getMetroCards();

	    List<CardHistory> history = cards.stream()
	            .flatMap(card -> card.getHistory().stream())
	            .toList();
	    
	    System.out.println(email);

	    return ResponseEntity.ok(new CardAndCardHistory(cards, history));
	}
	
	
	@PostMapping("/recharge")
    public ResponseEntity<?> rechargeCard(
            @RequestBody RechargeResponse rechargeResponse,
            Authentication authentication) {

        // 🔐 Optional extra security: ensure card belongs to logged-in user
        String email = authentication.getName();
        
        Customer customer =  customerRepository.findByEmailId(email).orElseThrow();
        
        MetroCard card = metroCardRepository.findById(rechargeResponse.getCardId())
                .orElseThrow(() -> new RuntimeException("Card not found"));
        
        card.setBalance(card.getBalance() + rechargeResponse.getNewBalance()) ;
        metroCardRepository.save(card);
       
        return ResponseEntity.ok("Recharge successful");
    }
	
	
	@PostMapping("/card-history")
	public ResponseEntity<?> addCardHistory(
	        @RequestBody CardHistoryRequest request,
	        Authentication authentication) {

	    String email = authentication.getName();

	    // 1️⃣ Find card by card number
	    MetroCard card = metroCardRepository
	            .findByCardNumber(request.getCardNumber())
	            .orElseThrow(() -> new RuntimeException("Invalid card number"));

	    // 2️⃣ Ensure card belongs to logged-in customer
	    if (!card.getCustomer().getEmailId().equals(email)) {
	        throw new RuntimeException("Unauthorized card access");
	    }

	    // 3️⃣ Check balance
	    if (card.getBalance() < request.getFare()) {
	        throw new RuntimeException("Insufficient balance");
	    }

	    // 4️⃣ Deduct fare
	    card.setBalance(card.getBalance() - request.getFare());

	    // 5️⃣ Save history
	    CardHistory history = new CardHistory();
	    history.setCard(card);
	    history.setInDate(request.getInDate());
	    history.setInTime(request.getInTime());
	    history.setOutTime(request.getOutTime());
	    history.setInStationName(request.getInStationName());
	    history.setOutStationName(request.getOutStationName());
	    history.setFare(request.getFare());
	    

	    cardHistoryRepository.save(history);
	    metroCardRepository.save(card);

	    return ResponseEntity.ok("Journey saved & fare deducted");
	}


	
}
