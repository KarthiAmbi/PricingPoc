package com.poc.pricing.dao.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
@NamedQuery(name = "ProductDo.findProductNameExists", query = "select p from ProductDo p where p.name =:name and p.id !=:id")
public class ProductDo {

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	private String name;
	private String description;
	private String type;
	private BigDecimal amount;
	@Version
	private Integer version;

	@OneToMany(cascade = CascadeType.PERSIST,  fetch = FetchType.LAZY)
	private List<Vendor> vendors;

}
