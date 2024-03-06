package com.hubs.ing.assignment.rest.api.converter;

import org.springframework.core.convert.converter.Converter;

import com.hubs.ing.assignment.database.entities.Product;
import com.hubs.ing.assignment.models.response.FetchProductResponseDto;

public class FetchProductConverter implements Converter<Product, FetchProductResponseDto> {
	@Override
	public FetchProductResponseDto convert(Product source) {
		return new FetchProductResponseDto(source.getId(), source.getName(), source.getDescription(), source.getPrice());
	}
}
