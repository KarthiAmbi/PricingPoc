package com.poc.pricing.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ProductDto {

	private Long id;
	@NotNull(message = "Product name cannot be null")
	private String name;
	private String description;
	@NotNull(message = "Product Type cannot be null")
	private String type;
	private String amount;

}
