package com.poc.pricing.dto;

import java.util.List;

import javax.validation.Valid;
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
public class VendorDto {
	private Long id;
	@NotNull(message = "Vendor name cannot be null")
	@NotEmpty(message = "Vendor name cannot be empty")
	private String name;
	private String description;
	private String address;
	@Valid
	private List<Review> reviews;

}
