package com.poc.pricing.dao;

import java.util.List;

import com.poc.pricing.dao.model.Vendor;

public interface VendorRepository extends BaseRepository<Vendor, Long> {
	
	List<Vendor> findAllVendorByNameDescending();
	

}
