package com.poc.pricing.rest;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.poc.pricing.model.ProductDto;
import com.poc.pricing.repository.ProductDo;
import com.poc.pricing.service.PricingServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class PricingControllerTest {

	@Mock
	PricingServiceImpl pricingService;

	@InjectMocks
	PricingController pricingController;

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
	public void getAllProductsTest(){

		Mockito.when(pricingService.getAllProducts()).thenReturn(listDto);
		List<ProductDto> l=pricingController.getAllProducts();
		Assert.assertTrue(l.size()==3);

	}

	@Test
	public void getProductTest(){

		Mockito.when(pricingService.findProductById(Mockito.any())).thenReturn(listDto.get(0));
		ProductDto l=pricingController.getProduct(1l);
		Assert.assertTrue(l.getId()==1);

	}

	@Test
	public void updateProductTest(){

		Mockito.when(pricingService.updateProduct(listDto.get(1),2l)).thenReturn(listDto.get(1));
		ProductDto l=pricingController.updateProduct(listDto.get(1),2l);
		Assert.assertTrue(l.getName().equals("TV"));

	}
	@Test
	public void createProductTest(){

		Mockito.when(pricingService.createProduct(Mockito.any())).thenReturn(listDto.get(0));
		ProductDto l=pricingController.createProduct(listDto.get(0));
		Assert.assertTrue(l.getId()==1);

	}

	@Test
	public void deleteProductTest(){

		pricingController.deleteProduct(1l);

	}




}
