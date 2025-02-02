package com.poc.pricing.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poc.pricing.dto.ErrorDetail;
import com.poc.pricing.util.PricingConstants;

@ControllerAdvice
public class PricingExceptionHandler {

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<Object> exception(PriceNotFoundException exception) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), HttpStatus.OK.toString(),
				PricingConstants.SUCCESS);
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}
	
	@ExceptionHandler(ProductNameExistException.class)
	public ResponseEntity<Object> exception(ProductNameExistException exception) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), "Product Name already exist", HttpStatus.OK.toString(),
				PricingConstants.VAILDATION_ERROR);
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgumentexception(MethodArgumentNotValidException response) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(),
				response.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.OK.toString(),
				PricingConstants.VAILDATION_ERROR);
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> commonException(Exception exception) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(),
				HttpStatus.EXPECTATION_FAILED.toString(), PricingConstants.FAILURE);
		return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}

}
