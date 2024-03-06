package com.hubs.ing.assignment.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hubs.ing.assignment.exceptions.BadRequestException;
import com.hubs.ing.assignment.exceptions.InternalServerErrorException;
import com.hubs.ing.assignment.models.response.Response;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandlerBase extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InternalServerErrorException.class })
	protected ResponseEntity<Response> handleBadRequestException(InternalServerErrorException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Response(null, Boolean.FALSE, List.of(ex.getMessage())));
	}

	@ExceptionHandler(value = { BadRequestException.class })
	protected ResponseEntity<Response> handleBadRequestException(BadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response(null, Boolean.FALSE, List.of(ex.getMessage())));
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	protected ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response(null, Boolean.FALSE,
								ex.getConstraintViolations().stream()
										.map(ConstraintViolation::getMessageTemplate)
										.toList()
						)
				);
	}
}
