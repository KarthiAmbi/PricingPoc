package com.poc.pricing.service;

import java.util.List;

import com.poc.pricing.model.PricingDto;

public interface PricingService {

	public PricingDto findPriceById(Long id);

	public PricingDto updatePricing(PricingDto pricing, Long id);

	public List<PricingDto> getAllPricing();

	public PricingDto createPricing(PricingDto pricing);
	
	public void deletePricing(long id);
	
}
