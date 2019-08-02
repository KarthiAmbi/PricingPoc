package com.poc.pricing.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

	private Long id;
	@NotNull(message = "Product name cannot be null")
	private String name;
	@NotNull(message = "Product Dept cannot be null")
	private String dept;
	private String amount;

}
