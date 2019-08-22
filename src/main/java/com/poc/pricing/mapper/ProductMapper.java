package com.poc.pricing.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.poc.pricing.dao.model.ProductDo;
import com.poc.pricing.dao.model.Vendor;
import com.poc.pricing.dto.ProductDto;
import com.poc.pricing.dto.VendorDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	public List<ProductDto> mapProductDoToDtoList(final List<ProductDo> productList);

	public ProductDo mapProductDtoToDo(final ProductDto product);

	
	public ProductDto mapProductDoToDto(final ProductDo productDo);
	
	public List<VendorDto> mapVendor(final List<Vendor> vendors);

}
