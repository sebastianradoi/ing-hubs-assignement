package com.hubs.ing.assignment.rest.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hubs.ing.assignment.models.request.ProductRequestDto;
import com.hubs.ing.assignment.models.response.Response;
import com.hubs.ing.assignment.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import lombok.RequiredArgsConstructor;

@SecuritySchemes(
		{
				@SecurityScheme(
						name = "basicAuth",
						type = SecuritySchemeType.HTTP,
						scheme = "basic"
				)
		})
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	@PostMapping(
			produces = APPLICATION_JSON_VALUE,
			consumes = APPLICATION_JSON_VALUE
	)
	@Operation(summary = "Add a new product", security = { @SecurityRequirement(name = "basicAuth") })
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201", description = "Created", content = {
							@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class)) }),
					@ApiResponse(
							responseCode = "400", description = "Bad Request", content = {
							@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class)) }),
					@ApiResponse(
							responseCode = "500", description = "Internal Server Error", content = {
							@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class)) }),
			})
	public ResponseEntity<Response> addProduct(
			@RequestBody ProductRequestDto request
	) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(request));
	}

}
