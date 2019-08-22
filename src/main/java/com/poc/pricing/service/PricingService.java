package com.poc.pricing.service;

import java.util.List;

import com.poc.pricing.dto.ProductDto;
import com.poc.pricing.dto.ProductResponse;
import com.poc.pricing.dto.VendorDto;

public interface PricingService {

	public ProductDto findProductById(Long id);

	public ProductDto updateProduct(ProductDto product, Long id);

	public ProductResponse getAllProducts(Integer pageNo, Integer pageSize, String sortBy);

	public ProductDto createProduct(ProductDto product);
	
	public void deleteProduct(long id);
	
	public List<VendorDto> findAllVendors();
	
}
