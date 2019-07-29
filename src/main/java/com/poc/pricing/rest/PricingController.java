package com.poc.pricing.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poc.pricing.exception.PriceNotFoundException;
import com.poc.pricing.model.Pricing;
import com.poc.pricing.service.PricingService;

@RestController
public class PricingController {

	@Autowired
	private PricingService pricingService;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/allpricing")
	public List<Pricing> getAllPricing() {
		return pricingService.getAllPricing();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/price/{id}")
	public Pricing findPricing(@PathVariable Long id) {
		return pricingService.findPriceById(id);
	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	@PutMapping("/price/{id}")
	public Pricing updatePrice(@RequestBody Pricing pricing, @PathVariable Long id) {
		return pricingService.updatePricing(pricing, id);
	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/pricing")
	public Pricing createPricing(@RequestBody Pricing pricing) {
		return pricingService.createPricing(pricing);
	}
}
