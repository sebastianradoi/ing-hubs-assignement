package com.hubs.ing.assignment.services;

import java.util.Optional;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.hubs.ing.assignment.database.entities.Product;
import com.hubs.ing.assignment.database.repository.ProductRepository;
import com.hubs.ing.assignment.exceptions.BadRequestException;
import com.hubs.ing.assignment.exceptions.InternalServerErrorException;
import com.hubs.ing.assignment.models.request.ProductRequestDto;
import com.hubs.ing.assignment.models.response.FetchProductResponseDto;
import com.hubs.ing.assignment.models.response.Response;
import com.hubs.ing.assignment.validation.IsPatchProductRequestValid;
import com.hubs.ing.assignment.validation.IsProductRequestValid;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class ProductService {

	private final ProductRepository productRepository;
	private final ConversionService conversionService;
	private final ResponseService responseService;

	@IsProductRequestValid
	public Response addProduct(ProductRequestDto request) {
		var product = Optional.ofNullable(conversionService.convert(request, Product.class))
				.orElseThrow(() -> new InternalServerErrorException("Failed to convert ProductRequestDto to Product"));

		productRepository.save(product);
		return responseService.createSuccessResponse(product.getId(), "Product added successfully");
	}

	public FetchProductResponseDto fetchService(String name) {
		var product = productRepository.findByName(name).orElseThrow(() -> new BadRequestException("Product not found"));
		return conversionService.convert(product, FetchProductResponseDto.class);
	}

	@IsPatchProductRequestValid
	public Response patchProduct(String name, ProductRequestDto request) {
		var product = productRepository.findByName(name).orElseThrow(() -> new BadRequestException("Product not found"));
		Optional.ofNullable(request.name()).ifPresent(product::setName);
		Optional.ofNullable(request.description()).ifPresent(product::setDescription);
		Optional.ofNullable(request.price()).ifPresent(product::setPrice);
		productRepository.save(product);
		return responseService.createSuccessResponse(product.getId(), "Product updated successfully");
	}
}
