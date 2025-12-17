package com.metro.metrobackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.metrobackend.models.MetroCard;

public interface MetroCardRepository extends JpaRepository<MetroCard, Long>{

	Optional<MetroCard> findByCardNumber(String cardNumber);
}
