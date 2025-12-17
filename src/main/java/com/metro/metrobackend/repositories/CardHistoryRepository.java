package com.metro.metrobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.metrobackend.models.CardHistory;

public interface CardHistoryRepository extends JpaRepository<CardHistory, Long> {

}
