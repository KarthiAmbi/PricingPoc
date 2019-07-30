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

import com.poc.pricing.model.PricingDto;
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
	@GetMapping("/allpricing")
	public List<PricingDto> getAllPricing() {
		return pricingService.getAllPricing();
	}

	/**
	 * This method fetches price by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/price/{id}")
	public PricingDto findPricing(@PathVariable Long id) {
		return pricingService.findPriceById(id);
	}

	/**
	 * This method updates price by id
	 * 
	 * @param pricing
	 * @return
	 */
	@PutMapping("/price/{id}")
	public PricingDto updatePrice(@RequestBody PricingDto pricing, @PathVariable Long id) {
		return pricingService.updatePricing(pricing, id);
	}

	/**
	 * This method creates the pricing
	 * 
	 * @param pricing
	 * @return
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/pricing")
	public PricingDto createPricing(@RequestBody PricingDto pricing) {
		return pricingService.createPricing(pricing);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/price/{id}")
	public void deletePricing(@PathVariable long id) {
		pricingService.deletePricing(id);
	}
}
