package com.poc.pricing.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDo {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String type;
	private String amount;

	

}
