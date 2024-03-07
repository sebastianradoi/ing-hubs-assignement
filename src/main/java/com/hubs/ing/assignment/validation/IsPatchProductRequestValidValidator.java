package com.hubs.ing.assignment.validation;

import org.springframework.stereotype.Service;

import com.hubs.ing.assignment.models.request.ProductRequestDto;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import lombok.RequiredArgsConstructor;

@Service
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
@RequiredArgsConstructor
public class IsPatchProductRequestValidValidator implements ConstraintValidator<IsPatchProductRequestValid, Object[]> {

	@Override
	public void initialize(IsPatchProductRequestValid constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	@Transactional
	public boolean isValid(Object[] value, ConstraintValidatorContext context) {
		var request = (ProductRequestDto) value[1];
		var res = true;
		if (request.price() != null && request.price() < 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Product price cannot be negative").addConstraintViolation();
			res = false;
		}

		return res;
	}
}
