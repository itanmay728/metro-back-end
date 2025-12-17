package com.metro.metrobackend.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

//    @JsonProperty("full_name")
	@Column(name = "full_name", nullable = false)
	private String fullName;

//    @JsonProperty("email_id")
	@Column(name = "email_id", nullable = false, unique = true)
	private String emailId;

//    @JsonProperty("phone_number")
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "address")
	private String address;

//    @JsonProperty("aadhar_number")
	@Column(name = "aadhar_number", nullable = false, unique = true, length = 12)
	private String aadharNumber;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	/*
	 * =============================== METRO CARD MAPPING
	 * ===============================
	 */
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MetroCard> metroCards;

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", fullName=" + fullName + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", aadharNumber=" + aadharNumber
				+ ", password=" + password + ", createdAt=" + createdAt + ", user=" + user + ", metroCards="
				+ metroCards + "]";
	}
	
	

}
