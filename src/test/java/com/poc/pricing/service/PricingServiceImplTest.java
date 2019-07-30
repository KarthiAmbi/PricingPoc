package com.poc.pricing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.poc.pricing.exception.PriceNotFoundException;
import com.poc.pricing.model.ProductDto;
import com.poc.pricing.repository.ProductDo;
import com.poc.pricing.repository.PricingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PricingServiceImplTest {

	@InjectMocks
	PricingServiceImpl pricingServiceImpl;
	
	@Mock
	PricingRepository pricingRepository;
	
	@Mock
	PricingMapper pricingMapper;
	

	List<ProductDo> listDo = new ArrayList<ProductDo>();
	List<ProductDto> listDto = new ArrayList<ProductDto>();
	
	@Before
	public void setUp(){

		listDo.add(new ProductDo(1l,"Fridge","Electrical","15000"));
		listDo.add(new ProductDo(2l,"TV","Electronics","10000"));
		listDo.add(new ProductDo(3l,"Router","Electronics","5000"));
		
		listDto.add(new ProductDto(1l,"Fridge","Electrical","15000"));
		listDto.add(new ProductDto(2l,"TV","Electronics","10000"));
		listDto.add(new ProductDto(3l,"Router","Electronics","5000"));
	}

	@Test
	public void getAllPricingTest(){

		Mockito.when(pricingRepository.findAll()).thenReturn(listDo);
		Mockito.when(pricingMapper.mapProductDoToDtoList(Mockito.any())).thenReturn(listDto);
		List<ProductDto> l=pricingServiceImpl.getAllProducts();
		Assert.assertTrue(l.size()==3);

	}
	
	@Test
	public void findPriceByIdTest(){
	
		Optional<ProductDo> pd = Optional.of(listDo.get(2));
		Mockito.when(pricingRepository.findById(3l)).thenReturn(pd);
		Mockito.when(pricingMapper.mapProductDoToDto(Mockito.any())).thenReturn(listDto.get(2));
		ProductDto dto = pricingServiceImpl.findProductById(3l);
		Assert.assertTrue(dto.getId()==3);

	}
	
	public ProductDto updatePricing(ProductDto pricing, Long id) {
		Optional<ProductDo> pricingDOOPt = pricingRepository.findById(id);
		if (pricingDOOPt.isPresent()) {
			ProductDo pricingDO = pricingMapper.mapProductDtoToDo(pricing);
			pricingDO.setId(id);
			pricingDO = pricingRepository.save(pricingDO);
			return pricingMapper.mapProductDoToDto(pricingDO);
		}
		throw new PriceNotFoundException();
	}
	
	@Test
	public void updatePricingTest(){
	
		Optional<ProductDo> pd = Optional.of(listDo.get(0));
		Mockito.when(pricingRepository.findById(1l)).thenReturn(pd);
		Mockito.when(pricingMapper.mapProductDtoToDo(Mockito.any())).thenReturn(listDo.get(0));
		Mockito.when(pricingMapper.mapProductDoToDto(Mockito.any())).thenReturn(listDto.get(0));
		Mockito.when(pricingRepository.save(Mockito.any())).thenReturn(listDo.get(0));
		ProductDto dto = pricingServiceImpl.updateProduct(listDto.get(0),1l);
		Assert.assertTrue(dto.getId()==1);

	}

}
