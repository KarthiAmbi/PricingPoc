package com.poc.pricing.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import io.micrometer.core.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Vendor.findAllVendorByNameDescending",	query = "SELECT v FROM Vendor v ORDER BY v.vendorName DESC")
public class Vendor {

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long vendorId;

	private String vendorName;

	@ManyToOne
	private ProductDo product;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ReviewDo> reviews;

}
