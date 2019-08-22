package com.poc.pricing.exception;

public class PriceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PriceNotFoundException(String messString) {
		super(messString);
	}
}
