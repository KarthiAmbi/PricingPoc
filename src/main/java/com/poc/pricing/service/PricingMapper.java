package com.poc.pricing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.poc.pricing.model.ProductDto;
import com.poc.pricing.repository.ProductDo;

@Component
public class PricingMapper {


	public List<ProductDto> mapProductDoToDtoList(List<ProductDo> productList) {
		List<ProductDto> productDtoList = new ArrayList<ProductDto>(productList.size());
		productList.forEach((p)->
		{
			ProductDto product = new ProductDto();
			BeanUtils.copyProperties(p, product);
			productDtoList.add(product);
			
		});
		return productDtoList;
	}

	public ProductDo mapProductDtoToDo(ProductDto product) {
		ProductDo productDo = new ProductDo();
		BeanUtils.copyProperties(product, productDo);
		return productDo;
	}

	public ProductDto mapProductDoToDto(ProductDo productDo) {
		ProductDto pricing = new ProductDto();
		BeanUtils.copyProperties(productDo, pricing);
		return pricing;
	}

}
