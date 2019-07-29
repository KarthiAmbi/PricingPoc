package com.poc.pricing.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PricingExceptionHandler {

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<Object> exception(PriceNotFoundException exception) {
		return new ResponseEntity<>("Price not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PricingException.class)
	public ResponseEntity<Object> exception(PricingException response) throws IOException {
		return new ResponseEntity<>("Pricing Exception", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception response) throws IOException {
		return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
