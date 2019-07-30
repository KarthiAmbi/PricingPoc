package com.poc.pricing.service;

import java.util.List;

import com.poc.pricing.model.ProductDto;

public interface PricingService {

	public ProductDto findProductById(Long id);

	public ProductDto updateProduct(ProductDto product, Long id);

	public List<ProductDto> getAllProducts();

	public ProductDto createProduct(ProductDto product);
	
	public void deleteProduct(long id);
	
}
