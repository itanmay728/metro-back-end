package com.metro.metrobackend.dto;

public class RechargeResponse {

    
    private Long cardId;
    private Double newBalance;

    public RechargeResponse( Long cardId, Double newBalance) {
        
        this.cardId = cardId;
        this.newBalance = newBalance;
    }

    public Long getCardId() {
        return cardId;
    }

    public Double getNewBalance() {
        return newBalance;
    }
}
