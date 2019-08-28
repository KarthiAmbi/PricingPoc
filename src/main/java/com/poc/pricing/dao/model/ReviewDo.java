package com.poc.pricing.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.poc.pricing.dto.RatingEnum;

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
	private Integer rating;

	@ManyToOne
	private Vendor vendor;

}
