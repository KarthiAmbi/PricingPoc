package com.poc.pricing.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.micrometer.core.lang.NonNull;
import lombok.Data;

@Entity
@Data
public class ReviewDo {
	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	private String name;
	private String comments;

	@ManyToOne
	private Vendor vendor;

}
