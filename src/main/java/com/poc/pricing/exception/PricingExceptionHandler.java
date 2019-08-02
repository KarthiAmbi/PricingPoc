package com.poc.pricing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PricingExceptionHandler {

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<Object> exception(PriceNotFoundException exception) {
		return new ResponseEntity<>("Product Id not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgumentexception(MethodArgumentNotValidException  response) {
		return new ResponseEntity<>(response.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> exception(Exception response) {
		return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
