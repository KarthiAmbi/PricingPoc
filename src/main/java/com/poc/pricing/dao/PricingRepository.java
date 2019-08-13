package com.poc.pricing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.pricing.dao.model.ProductDo;

public interface PricingRepository extends JpaRepository<ProductDo, Long>{

}
