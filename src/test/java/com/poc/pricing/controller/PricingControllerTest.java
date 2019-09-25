package com.poc.pricing.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.poc.pricing.dto.ProductDto;
import com.poc.pricing.dto.Review;
import com.poc.pricing.dto.VendorDto;
import com.poc.pricing.service.PricingService;
@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(value = PricingController.class)
public class PricingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PricingService pricingService;

	ProductDto productDto = null;

	@Before
	public void setup() {
		List<VendorDto> vendors = new ArrayList<VendorDto>();
		List<Review> reviews = new ArrayList<Review>();
		Review review = new Review("KKKK", "Good Product", 5);
		reviews.add(review);
		VendorDto vendor = new VendorDto(1L, "Apple", "User Friendly", "USA", reviews);
		vendors.add(vendor);
		productDto = new ProductDto(1L, "4GMobile", "Work in 4G sets", "Mobile", BigDecimal.valueOf(100000), vendors);
	}

	@Test
	public void createProductTest() throws Exception {
		String json = "{ \"name\": \"4G Mobiles\", \"description\": \"High configuration mobile\", \"type\": \"Mobile\", \"amount\": \"100000\", \"vendors\": [ { \"name\": \"Apple\", \"description\": \"User Friendly\", \"address\" : \"USA\", \"reviews\": [ { \"name\":\"karthi\", \"comments\":\"good\", \"rating\" : 5 } ] } ] }";
		Mockito.when(pricingService.createProduct(Mockito.any())).thenReturn(productDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/product").accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void updateProductTest() throws Exception {
		String json = "{ \"id\":\"1\",\"name\": \"4G Mobiles\", \"description\": \"High configuration mobile\", \"type\": \"Mobile\", \"amount\": \"100000\", \"vendors\": [ { \"name\": \"Apple\", \"description\": \"User Friendly\", \"address\" : \"USA\", \"reviews\": [ { \"name\":\"karthi\", \"comments\":\"good\", \"rating\" : 5 } ] } ] }";
		productDto.setId(1L);
		Mockito.when(pricingService.updateProduct(Mockito.any(), Mockito.any())).thenReturn(productDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/product/1").accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void deleteProductTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/product/1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
	}

}
