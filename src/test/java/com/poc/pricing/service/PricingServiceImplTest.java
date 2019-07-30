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
import com.poc.pricing.model.PricingDto;
import com.poc.pricing.repository.PricingDo;
import com.poc.pricing.repository.PricingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PricingServiceImplTest {

	@InjectMocks
	PricingServiceImpl pricingServiceImpl;
	
	@Mock
	PricingRepository pricingRepository;
	
	@Mock
	PricingMapper pricingMapper;
	

	List<PricingDo> listDo = new ArrayList<PricingDo>();
	List<PricingDto> listDto = new ArrayList<PricingDto>();
	
	@Before
	public void setUp(){

		listDo.add(new PricingDo(1l,"Fridge","Electrical","15000"));
		listDo.add(new PricingDo(2l,"TV","Electronics","10000"));
		listDo.add(new PricingDo(3l,"Router","Electronics","5000"));
		
		listDto.add(new PricingDto(1l,"Fridge","Electrical","15000"));
		listDto.add(new PricingDto(2l,"TV","Electronics","10000"));
		listDto.add(new PricingDto(3l,"Router","Electronics","5000"));
	}

	@Test
	public void getAllPricingTest(){

		Mockito.when(pricingRepository.findAll()).thenReturn(listDo);
		Mockito.when(pricingMapper.mapPricingDoToDtoList(Mockito.any())).thenReturn(listDto);
		List<PricingDto> l=pricingServiceImpl.getAllPricing();
		Assert.assertTrue(l.size()==3);

	}
	
	@Test
	public void findPriceByIdTest(){
	
		Optional<PricingDo> pd = Optional.of(listDo.get(2));
		Mockito.when(pricingRepository.findById(3l)).thenReturn(pd);
		Mockito.when(pricingMapper.mapPricingDoToDto(Mockito.any())).thenReturn(listDto.get(2));
		PricingDto dto = pricingServiceImpl.findPriceById(3l);
		Assert.assertTrue(dto.getId()==3);

	}
	
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
	
	@Test
	public void updatePricingTest(){
	
		Optional<PricingDo> pd = Optional.of(listDo.get(0));
		Mockito.when(pricingRepository.findById(1l)).thenReturn(pd);
		Mockito.when(pricingMapper.mapPricingDtoToDo(Mockito.any())).thenReturn(listDo.get(0));
		Mockito.when(pricingMapper.mapPricingDoToDto(Mockito.any())).thenReturn(listDto.get(0));
		Mockito.when(pricingRepository.save(Mockito.any())).thenReturn(listDo.get(0));
		PricingDto dto = pricingServiceImpl.updatePricing(listDto.get(0),1l);
		Assert.assertTrue(dto.getId()==1);

	}

}
