package com.hubs.ing.assignment.rest.api.converter;

import org.springframework.core.convert.converter.Converter;

import com.hubs.ing.assignment.database.entities.Product;
import com.hubs.ing.assignment.models.request.ProductRequestDto;

public class ProductConverter implements Converter<ProductRequestDto, Product> {
	@Override
	public Product convert(ProductRequestDto source) {
		var product = new Product();
		product.setName(source.name());
		product.setDescription(source.description());
		product.setPrice(source.price());
		return product;
	}
}
