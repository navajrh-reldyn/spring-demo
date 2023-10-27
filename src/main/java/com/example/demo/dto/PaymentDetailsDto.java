package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsDto {

	private String transactionId;
	private String from;
	private String to;
	private LocalDateTime dateTime;
	private boolean status;
	private double amount;
	private String method;
	private String bankName;
}
