package com.poc.pricing.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricingDo {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String dept;
	private String amount;

	

}
