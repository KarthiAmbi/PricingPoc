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
import lombok.extern.slf4j.Slf4j;

@Api
@RestController
@Slf4j
public class PricingController {

	@Autowired
	private PricingServiceImpl pricingService;

	/**
	 * This method returns all the products
	 * 
	 * @return
	 */
	@GetMapping("/products")
	public List<ProductDto> getAllProducts() {
		log.info(" :: Inside PricingController getAllProducts ::");
		return pricingService.getAllProducts();
	}

	/**
	 * This method fetches product by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/product/{id}")
	public ProductDto getProduct(@PathVariable Long id) {
		return pricingService.findProductById(id);
	}

	/**
	 * This method updates product by id
	 * 
	 * @param product
	 * @return
	 */
	@PutMapping("/product/{id}")
	public ProductDto updateProduct(@RequestBody ProductDto product, @PathVariable Long id) {
		return pricingService.updateProduct(product, id);
	}

	/**
	 * This method creates the product
	 * 
	 * @param product
	 * @return
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/product")
	public ProductDto createProduct(@RequestBody ProductDto product) {
		return pricingService.createProduct(product);
	}

	/**
	 * This method deletes the product
	 * 
	 * @param id
	 */
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable long id) {
		pricingService.deleteProduct(id);
	}
}
