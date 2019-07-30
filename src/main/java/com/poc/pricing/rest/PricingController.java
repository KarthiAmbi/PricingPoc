package com.poc.pricing.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poc.pricing.model.ProductDto;
import com.poc.pricing.service.PricingServiceImpl;

import io.swagger.annotations.Api;

@Api
@RestController
public class PricingController {

	@Autowired
	private PricingServiceImpl pricingService;

	/**
	 * This method returns all the pricing entries
	 * 
	 * @return
	 */
	@GetMapping("/products")
	public List<ProductDto> getAllProducts() {
		return pricingService.getAllProducts();
	}

	/**
	 * This method fetches price by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/product/{id}")
	public ProductDto getProduct(@PathVariable Long id) {
		return pricingService.findProductById(id);
	}

	/**
	 * This method updates price by id
	 * 
	 * @param pricing
	 * @return
	 */
	@PutMapping("/product/{id}")
	public ProductDto updateProduct(@RequestBody ProductDto pricing, @PathVariable Long id) {
		return pricingService.updateProduct(pricing, id);
	}

	/**
	 * This method creates the pricing
	 * 
	 * @param pricing
	 * @return
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/product")
	public ProductDto createProduct(@RequestBody ProductDto pricing) {
		return pricingService.createProduct(pricing);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable long id) {
		pricingService.deleteProduct(id);
	}
}
