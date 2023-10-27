package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndividualPaymentDetailDto {
	private String transactionId;
	private Map<String, String> from;
	private Map<String, String> to;
	private LocalDateTime dateTime;
	private boolean status;
	private double amount;
	private String appTransactionId;
	private String method;
	private String bankName;
}
