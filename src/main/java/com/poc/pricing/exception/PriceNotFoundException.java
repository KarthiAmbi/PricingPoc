package com.poc.pricing.exception;

public class PriceNotFoundException extends RuntimeException {

	public PriceNotFoundException(String message) {
		super(message);
	}

	public PriceNotFoundException() {
	}
}
