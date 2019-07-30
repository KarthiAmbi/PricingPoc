package com.poc.pricing.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PricingDo {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String dept;
	private String amount;

	

}
