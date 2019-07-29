package com.poc.pricing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.poc.pricing.model.Pricing;
import com.poc.pricing.repository.PricingDO;
@Component
public class PricingMapper {


	public List<Pricing> mapPricingDotoDto(List<PricingDO> pricingList) {
		List<Pricing> pricingLi = new ArrayList<Pricing>(pricingList.size());
		pricingList.forEach((p)->
		{
			Pricing pricing = new Pricing();
			BeanUtils.copyProperties(p, pricing);
			pricingLi.add(pricing);
			
		});
		return pricingLi;
	}

	public PricingDO mapPricingDtotoDo(Pricing pricing) {
		PricingDO pricingDO = new PricingDO();
		BeanUtils.copyProperties(pricing, pricingDO);
		return pricingDO;
	}

	public Pricing mapPricingDtotoDoReverse(PricingDO pricingDO) {
		Pricing pricing = new Pricing();
		BeanUtils.copyProperties(pricingDO, pricing);
		return pricing;
	}

}
