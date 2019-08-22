package com.poc.pricing.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse {

	private String message;
	private String statusCode;
	private String status;
	private LocalDateTime localDate;

}
