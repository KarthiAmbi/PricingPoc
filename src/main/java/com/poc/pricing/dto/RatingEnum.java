package com.poc.pricing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RatingEnum {
	FIVE(5), FOUR(4), THREE(3), TWO(2), ONE(1);

	private Integer value;

	private RatingEnum(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	
}
