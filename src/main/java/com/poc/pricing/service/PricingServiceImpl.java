package com.poc.pricing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.poc.pricing.exception.PriceNotFoundException;
import com.poc.pricing.model.PricingDto;
import com.poc.pricing.repository.PricingDo;
import com.poc.pricing.repository.PricingRepository;

@Component
public class PricingServiceImpl implements PricingService {

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
	public PricingDto findPriceById(Long id) throws PriceNotFoundException {
		Optional<PricingDo> pricingDO = pricingRepository.findById(id);
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
	public PricingDto updatePricing(PricingDto pricing, Long id) {
		Optional<PricingDo> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			PricingDo pricingDO = pricingMapper.mapPricingDtoToDo(pricing);
			pricingDO.setId(id);
			pricingDO = pricingRepository.save(pricingDO);
			return pricingMapper.mapPricingDoToDto(pricingDO);
		}
		throw new PriceNotFoundException();
	}

	/**
	 * 
	 * @return
	 */
	public List<PricingDto> getAllPricing() {
		List<PricingDo> pricingList = pricingRepository.findAll();
		if (!CollectionUtils.isEmpty(pricingList)) {
			return pricingMapper.mapPricingDoToDtoList(pricingList);
		}
		throw new PriceNotFoundException();
	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	public PricingDto createPricing(PricingDto pricing) {
		PricingDo pricingDO = pricingRepository.save(pricingMapper.mapPricingDtoToDo(pricing));
		return pricingMapper.mapPricingDoToDto(pricingDO);

	}

	
	/**
	 * This method deletes the pricing by id
	 * 
	 * @param id
	 */
	public void deletePricing(long id) {
		Optional<PricingDo> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			pricingRepository.deleteById(id);
		} else {
			throw new PriceNotFoundException();
		}
	}
}
