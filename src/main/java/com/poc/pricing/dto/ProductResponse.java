package com.poc.pricing.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponse {

	private long totalNumberofRecords;
	private List<ProductDto> products;

}
