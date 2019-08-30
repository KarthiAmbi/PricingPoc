package com.poc.pricing.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Review {
	@NotNull(message = "Reviewr name name cannot be null")
	@NotEmpty(message = "Reviewr name cannot be empty")
	private String name;
	private String comments;
	private Integer rating;

}
