package com.poc.pricing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.poc.pricing.exception.PriceNotFoundException;
import com.poc.pricing.model.ProductDto;
import com.poc.pricing.repository.ProductDo;
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
	public ProductDto findProductById(Long id) throws PriceNotFoundException {
		Optional<ProductDo> pricingDO = pricingRepository.findById(id);
		if (pricingDO.isPresent()) {
			return pricingMapper.mapProductDoToDto(pricingDO.get());
		}
		throw new PriceNotFoundException();

	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	public ProductDto updateProduct(ProductDto product, Long id) {
		Optional<ProductDo> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			ProductDo pricingDO = pricingMapper.mapProductDtoToDo(product);
			pricingDO.setId(id);
			pricingDO = pricingRepository.save(pricingDO);
			return pricingMapper.mapProductDoToDto(pricingDO);
		}
		throw new PriceNotFoundException();
	}

	/**
	 * 
	 * @return
	 */
	public List<ProductDto> getAllProducts() {
		List<ProductDo> pricingList = pricingRepository.findAll();
		if (!CollectionUtils.isEmpty(pricingList)) {
			return pricingMapper.mapProductDoToDtoList(pricingList);
		}
		throw new PriceNotFoundException();
	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	public ProductDto createProduct(ProductDto product) {
		ProductDo pricingDO = pricingRepository.save(pricingMapper.mapProductDtoToDo(product));
		return pricingMapper.mapProductDoToDto(pricingDO);

	}

	
	/**
	 * This method deletes the pricing by id
	 * 
	 * @param id
	 */
	public void deleteProduct(long id) {
		Optional<ProductDo> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			pricingRepository.deleteById(id);
		} else {
			throw new PriceNotFoundException();
		}
	}
}
