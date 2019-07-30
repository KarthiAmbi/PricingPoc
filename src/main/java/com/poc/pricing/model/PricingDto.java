package com.poc.pricing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PricingDto {

	private Long id;
	private String name;
	private String dept;
	private String amount;

}
