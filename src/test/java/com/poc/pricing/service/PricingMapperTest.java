package com.poc.pricing.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poc.pricing.dao.model.ProductDo;
import com.poc.pricing.dto.ProductDto;
import com.poc.pricing.mapper.PricingMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class PricingMapperTest {

	@InjectMocks
	PricingMapper pricingMapper;

	List<ProductDo> listDo = new ArrayList<ProductDo>();

	@Before
	public void setUp() {

		listDo.add(new ProductDo(1l, "Fridge", "Home Applience", "Electrical", "15000"));
		listDo.add(new ProductDo(2l, "TV", "Electronics", "Home Applience", "10000"));
		listDo.add(new ProductDo(3l, "Router", "Electronics", "Home Applience", "5000"));

	}

	@Test
	public void testMapProductDoToDtoList() {
		List<ProductDto> productDtoList = pricingMapper.mapProductDoToDtoList(listDo);
		assertNotNull(productDtoList);
	}

	@Test
	public void testMapProductDtoToDo() {
		ProductDo productDo = pricingMapper
				.mapProductDtoToDo(new ProductDto(1l, "Fridge", "Home Appl", "Electrical", "15000"));
		assertNotNull(productDo);
	}

	@Test
	public void testMapProductDoToDto() {
		ProductDto productDto = pricingMapper
				.mapProductDoToDto(new ProductDo(3l, "Router", "Home Appl", "Electronics", "5000"));
		assertNotNull(productDto);
	}
}
