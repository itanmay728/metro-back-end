package com.metro.metrobackend.dto;

import java.util.List;

import com.metro.metrobackend.models.CardHistory;
import com.metro.metrobackend.models.MetroCard;

public class CardAndCardHistory {

	private List<MetroCard> metroCard;
	private List<CardHistory> cardHistory;
	
	public CardAndCardHistory(List<MetroCard> metroCard, List<CardHistory> cardHistory) {
		this.cardHistory = cardHistory;
		this.metroCard = metroCard;
	}

	public List<MetroCard> getMetroCard() {
		return metroCard;
	}

	public void setMetroCard(List<MetroCard> metroCard) {
		this.metroCard = metroCard;
	}

	public List<CardHistory> getCardHistory() {
		return cardHistory;
	}

	public void setCardHistory(List<CardHistory> cardHistory) {
		this.cardHistory = cardHistory;
	}

	
	
	
	
}
