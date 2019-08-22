package com.poc.pricing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse {

	private String message;
	private String statusCode;
	private String status;

}
