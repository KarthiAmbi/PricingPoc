package com.poc.pricing.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.poc.pricing.dao.model.ProductDo;
import com.poc.pricing.dao.model.Vendor;
import com.poc.pricing.dto.ProductDto;
import com.poc.pricing.dto.VendorDto;

@Component
public class PricingMapper {

	public List<ProductDto> mapProductDoToDtoList(final List<ProductDo> productList) {
		List<ProductDto> productDtoList = new ArrayList<>(productList.size());
		productList.forEach(p -> {
			ProductDto product = mapProductDoToDto(p);
			productDtoList.add(product);

		});
		return productDtoList;
	}

	public ProductDo mapProductDtoToDo(final ProductDto product) {
		ProductDo productDo = new ProductDo();
		List<Vendor> vendors = new ArrayList<>();
		BeanUtils.copyProperties(product, productDo);
		if (product.getVendors() != null && !product.getVendors().isEmpty()) {
			product.getVendors().forEach((v) -> {
				Vendor vendor = new Vendor();
				BeanUtils.copyProperties(v, vendor);
				vendors.add(vendor);
			});
		}
		productDo.setVendors(vendors);
		return productDo;
	}

	public ProductDto mapProductDoToDto(final ProductDo productDo) {
		ProductDto pricing = new ProductDto();
		BeanUtils.copyProperties(productDo, pricing);
		List<VendorDto> vendors = new ArrayList<>();
		if (productDo.getVendors() != null && !productDo.getVendors().isEmpty()) {
			productDo.getVendors().forEach((v) -> {
				VendorDto vendorDto = new VendorDto();
				BeanUtils.copyProperties(v, vendorDto);
				vendors.add(vendorDto);
			});
		}
		pricing.setVendors(vendors);
		return pricing;
	}

}
