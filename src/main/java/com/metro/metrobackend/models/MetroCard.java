package com.metro.metrobackend.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "metro_cards")
public class MetroCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "card_number", nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Column(nullable = false)
    private Double balance;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate = LocalDate.now();

    @Column(name = "card_status", nullable = false)
    private String status; // ACTIVE, BLOCKED, EXPIRED

    /* ==============================
       CUSTOMER MAPPING
       ============================== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    /* ==============================
       CARD HISTORY MAPPING
       ============================== */

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardHistory> history;
}
