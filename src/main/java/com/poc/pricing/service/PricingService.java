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
			return pricingMapper.mapPricingDtotoDoReverse(pricingDO.get());
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
			PricingDO pricingDO = pricingMapper.mapPricingDtotoDo(pricing);
			pricingDO = pricingRepository.save(pricingDO);
			return pricingMapper.mapPricingDtotoDoReverse(pricingDO);
		}
		throw new PriceNotFoundException();
	}

	/**
	 * 
	 * @return
	 */
	public List<Pricing> getAllPricing() {
		List<PricingDO> pricingList = pricingRepository.findAll();
		return pricingMapper.mapPricingDotoDto(pricingList);
	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	public Pricing createPricing(Pricing pricing) {
		PricingDO pricingDO = pricingRepository.save(pricingMapper.mapPricingDtotoDo(pricing));
		return pricingMapper.mapPricingDtotoDoReverse(pricingDO);

	}
}
