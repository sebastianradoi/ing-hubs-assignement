package com.hubs.ing.assignment.rest.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hubs.ing.assignment.models.request.UserRequestDto;
import com.hubs.ing.assignment.models.response.Response;
import com.hubs.ing.assignment.services.UserService;

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
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@PostMapping(
			produces = APPLICATION_JSON_VALUE,
			consumes = APPLICATION_JSON_VALUE
	)
	@Operation(summary = "Register a new user", security = { @SecurityRequirement(name = "basicAuth") })
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
	public ResponseEntity<Response> addUser(
			@RequestBody UserRequestDto request
	) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(request));
	}

	@DeleteMapping(
			path = "/{userId}",
			produces = APPLICATION_JSON_VALUE,
			consumes = APPLICATION_JSON_VALUE)
	@Operation(summary = "Delete an existing user", security = { @SecurityRequirement(name = "basicAuth") })
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200", description = "Success", content = {
							@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class)) }),
					@ApiResponse(
							responseCode = "400", description = "Bad Request", content = {
							@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class)) })
			})
	public ResponseEntity<Response> deleteUser(@PathVariable("userId") Long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.deleteUser(userId));
	}
}
