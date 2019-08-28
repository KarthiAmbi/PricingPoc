package com.poc.pricing.controller;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.pricing.dto.ProductDto;
import com.poc.pricing.dto.SuccessResponse;
import com.poc.pricing.service.PricingService;
import com.poc.pricing.util.PricingConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Pricing Poc")
@RestController
@Slf4j
@RequestMapping("/v1")
public class PricingController {

	@Autowired
	private PricingService pricingService;

	@ApiOperation(value = "Get all the products It accepts Pagination", response = Iterator.class)
	@GetMapping(value = "/products")
	public ResponseEntity<Object> findAllProducts(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		log.debug(" :: Start PricingController getAllProducts ::");
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(
				pricingService.getAllProducts(pageNo, pageSize, sortBy), HttpStatus.OK);
		log.debug(" :: End PricingController getAllProducts ::");
		return responseEntity;
	}

	@ApiOperation(value = "Get product using the product id", response = Iterator.class)
	@GetMapping(value = "/product/{id}")
	public ResponseEntity<Object> fingProduct(@PathVariable Long id) {
		log.debug(" :: Start PricingController fingProduct ::");
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(pricingService.findProductById(id), HttpStatus.OK);
		log.debug(" :: Start PricingController fingProduct ::");
		return responseEntity;
	}

	@ApiOperation(value = "Update the  product using the product id", response = ResponseEntity.class)
	@PutMapping(value = "/product/{id}")
	public ResponseEntity<Object> updateProduct(@Valid @RequestBody ProductDto product, @PathVariable Long id) {
		log.debug(" :: Start PricingController updateProduct ::");
		pricingService.updateProduct(product, id);
		SuccessResponse successResponse = new SuccessResponse("Product Changes has been updated Successfully",
				HttpStatus.CREATED.toString(), PricingConstants.SUCCESS, LocalDateTime.now());
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(successResponse, HttpStatus.CREATED);
		log.debug(" :: Start PricingController updateProduct ::");
		return responseEntity;
	}

	@ApiOperation(value = "Create the product", response = ResponseEntity.class)
	@PostMapping(value = "/product")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto product) {
		log.debug(" :: Start PricingController createProduct ::");
		pricingService.createProduct(product);
		SuccessResponse successResponse = new SuccessResponse("Product has been saved successfully",
				HttpStatus.OK.toString(), PricingConstants.SUCCESS, LocalDateTime.now());
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(successResponse, HttpStatus.OK);
		log.debug(" :: Start PricingController createProduct ::");
		return responseEntity;
	}

	@ApiOperation(value = "Create the  product using the product id", response = ResponseEntity.class)
	@DeleteMapping(value = "/product/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable long id) {
		log.debug(" :: Start PricingController deleteProduct ::");
		pricingService.deleteProduct(id);
		SuccessResponse successResponse = new SuccessResponse("Product has been deleted successfully",
				HttpStatus.ACCEPTED.toString(), PricingConstants.SUCCESS, LocalDateTime.now());
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED);
		log.debug(" :: Start PricingController deleteProduct ::");
		return responseEntity;
	}

	@ApiOperation(value = "Get all the Vendors", response = Iterator.class)
	@GetMapping(value = "/vendors")
	public ResponseEntity<Object> findAllVendors() {
		log.debug(" :: Start PricingController findAllVendors ::");
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(pricingService.findAllVendors(), HttpStatus.OK);
		log.debug(" :: End PricingController findAllVendors ::");
		return responseEntity;
	}
	
	@ApiOperation(value = "Get product using the product id as query param", response = Iterator.class)
	@GetMapping(value = "/product")
	public ResponseEntity<Object> fingProductByParam(@RequestParam("id") Optional<Long> itemid) {
		log.debug(" :: Start PricingController fingProduct ::");
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(pricingService.findProductById(itemid.get()), HttpStatus.OK);
		log.debug(" :: Start PricingController fingProduct ::");
		return responseEntity;
	}
}
