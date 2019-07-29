package com.poc.pricing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.pricing.exception.PriceNotFoundException;
import com.poc.pricing.model.Pricing;
import com.poc.pricing.repository.PricingDO;
import com.poc.pricing.repository.PricingRepository;

@Component
public class PricingService {

	@Autowired
	private PricingMapper pricingMapper;

	@Autowired
	private PricingRepository pricingRepository;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PriceNotFoundException
	 */
	public Pricing findPriceById(Long id) throws PriceNotFoundException {
		Optional<PricingDO> pricingDO = pricingRepository.findById(id);
		if (pricingDO.isPresent()) {
			return pricingMapper.mapPricingDoToDto(pricingDO.get());
		}
		throw new PriceNotFoundException();

	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	public Pricing updatePricing(Pricing pricing, Long id) {
		Optional<PricingDO> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			PricingDO pricingDO = pricingMapper.mapPricingDtoToDo(pricing);
			pricingDO = pricingRepository.save(pricingDO);
			return pricingMapper.mapPricingDoToDto(pricingDO);
		}
		throw new PriceNotFoundException();
	}

	/**
	 * 
	 * @return
	 */
	public List<Pricing> getAllPricing() {
		List<PricingDO> pricingList = pricingRepository.findAll();
		return pricingMapper.mapPricingDoToDtoList(pricingList);
	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	public Pricing createPricing(Pricing pricing) {
		PricingDO pricingDO = pricingRepository.save(pricingMapper.mapPricingDtoToDo(pricing));
		return pricingMapper.mapPricingDoToDto(pricingDO);

	}
}
