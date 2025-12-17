package com.metro.metrobackend.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "card_history")
public class CardHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "in_date", nullable = false)
    private LocalDate inDate;
    
    private String inStationName;
    
    private String outStationName;

    @Column(name = "in_time", nullable = false)
    private LocalTime inTime;

    @Column(name = "out_time")
    private LocalTime outTime;

    @Column(name = "fare")
    private Double fare;

    /* ==============================
       CARD MAPPING
       ============================== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    @JsonIgnore
    private MetroCard card;
}
