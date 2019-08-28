package com.poc.pricing.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;
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
	@NotEmpty(message = "Product name cannot be empty")
	private String name;
	private String description;
	@NotNull(message = "Product Type cannot be null")
	private String type;
	private BigDecimal amount;
	private List<VendorDto> vendors;
	
	

}
