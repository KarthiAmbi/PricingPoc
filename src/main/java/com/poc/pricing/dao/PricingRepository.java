package com.poc.pricing.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.poc.pricing.dao.model.ProductDo;

public interface PricingRepository
		extends BaseRepository<ProductDo, Long>, PagingAndSortingRepository<ProductDo, Long> {

	public Optional<ProductDo> findByName(String name);

	public Optional<ProductDo> findProductNameExists(@Param("name") String name, @Param("id") Long id);

}
