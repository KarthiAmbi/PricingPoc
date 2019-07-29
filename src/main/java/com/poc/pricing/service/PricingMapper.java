package com.poc.pricing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.poc.pricing.model.PricingDto;
import com.poc.pricing.repository.PricingDo;
@Component
public class PricingMapper {


	public List<PricingDto> mapPricingDoToDtoList(List<PricingDo> pricingList) {
		List<PricingDto> pricingLi = new ArrayList<PricingDto>(pricingList.size());
		pricingList.forEach((p)->
		{
			PricingDto pricing = new PricingDto();
			BeanUtils.copyProperties(p, pricing);
			pricingLi.add(pricing);
			
		});
		return pricingLi;
	}

	public PricingDo mapPricingDtoToDo(PricingDto pricing) {
		PricingDo pricingDO = new PricingDo();
		BeanUtils.copyProperties(pricing, pricingDO);
		return pricingDO;
	}

	public PricingDto mapPricingDoToDto(PricingDo pricingDO) {
		PricingDto pricing = new PricingDto();
		BeanUtils.copyProperties(pricingDO, pricing);
		return pricing;
	}

}
