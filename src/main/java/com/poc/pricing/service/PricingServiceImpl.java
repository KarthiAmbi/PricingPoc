package com.poc.pricing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.poc.pricing.dao.PricingRepository;
import com.poc.pricing.dao.VendorRepository;
import com.poc.pricing.dao.model.ProductDo;
import com.poc.pricing.dao.model.Vendor;
import com.poc.pricing.dto.ProductDto;
import com.poc.pricing.dto.ProductResponse;
import com.poc.pricing.dto.VendorDto;
import com.poc.pricing.exception.PriceNotFoundException;
import com.poc.pricing.exception.ProductNameExistException;
import com.poc.pricing.mapper.ProductMapper;
import com.poc.pricing.util.PricingConstants;

@Component
public class PricingServiceImpl implements PricingService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private PricingRepository pricingRepository;

	@Autowired
	private VendorRepository vendorRepository;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PriceNotFoundException
	 */
	@Cacheable(value = "productCache", key = "#id")
	public ProductDto findProductById(final Long id) {
		Optional<ProductDo> pricingDO = pricingRepository.findById(id);
		if (pricingDO.isPresent()) {
			return productMapper.mapProductDoToDto(pricingDO.get());
		}
		throw new PriceNotFoundException(PricingConstants.PRODUCT_NOT_FOUND_ERROR);

	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	@CachePut(value = "productCache", key = "#id")
	public ProductDto updateProduct(final ProductDto product, final Long id) {
		Optional<ProductDo> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			ProductDo pricingDO = productMapper.mapProductDtoToDo(product);
			pricingDO.setId(id);
			pricingDO = pricingRepository.save(pricingDO);
			return productMapper.mapProductDoToDto(pricingDO);
		}
		throw new PriceNotFoundException(PricingConstants.PRODUCT_NOT_FOUND_ERROR);
	}

	/**
	 * 
	 * @return
	 */
	public ProductResponse getAllProducts(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductDo> pagedResult = pricingRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			long count = pricingRepository.count();
			ProductResponse productResponse = new ProductResponse();
			productResponse.setTotalNumberofRecords(count);
			List<ProductDto> productDtos = productMapper.mapProductDoToDtoList(pagedResult.getContent());
			productResponse.setProducts(productDtos);
			return productResponse;
		}
		throw new PriceNotFoundException(PricingConstants.PRODUCT_NOT_FOUND_ERROR);

	}

	/**
	 * 
	 * @param pricing
	 * @return
	 */
	public ProductDto createProduct(final ProductDto product) {
		Optional<ProductDo> pro = pricingRepository.findByName(product.getName());
		if (!pro.isPresent()) {
			ProductDo productDo = productMapper.mapProductDtoToDo(product);
			ProductDo pricingDO = pricingRepository.save(productDo);
			return productMapper.mapProductDoToDto(pricingDO);
		}
		throw new ProductNameExistException("Product Name already exist");

	}

	/**
	 * This method deletes the pricing by id
	 * 
	 * @param id
	 */
	@CacheEvict(value = "productCache", key = "#id")
	public void deleteProduct(final long id) {
		Optional<ProductDo> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			pricingRepository.deleteById(id);
		} else {
			throw new PriceNotFoundException(PricingConstants.PRODUCT_NOT_FOUND_ERROR);
		}
	}

	@Override
	public List<VendorDto> findAllVendors() {
		List<Vendor> vendors = vendorRepository.findAllVendorByNameDescending();
		if (vendors != null) {
			return productMapper.mapVendor(vendors);
		}

		throw new PriceNotFoundException("Vendors Not Found");
	}
}
