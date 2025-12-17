package com.metro.metrobackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CardHistoryRequest {

    private String cardNumber;
    private LocalDate inDate;
    private LocalTime inTime;
    private LocalTime outTime;
    private String inStationName;
    private String outStationName;
    private Double fare;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getInDate() {
		return inDate;
	}
	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}
	public LocalTime getInTime() {
		return inTime;
	}
	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}
	public LocalTime getOutTime() {
		return outTime;
	}
	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}
	public String getInStationName() {
		return inStationName;
	}
	public void setInStationName(String inStationName) {
		this.inStationName = inStationName;
	}
	public String getOutStationName() {
		return outStationName;
	}
	public void setOutStationName(String outStationName) {
		this.outStationName = outStationName;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}

    
}
