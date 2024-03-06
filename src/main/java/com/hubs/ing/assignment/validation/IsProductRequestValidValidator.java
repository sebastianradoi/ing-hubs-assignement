package com.hubs.ing.assignment.validation;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hubs.ing.assignment.database.repository.ProductRepository;
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
public class IsProductRequestValidValidator implements ConstraintValidator<IsProductRequestValid, Object[]> {
	private final ProductRepository productRepository;

	@Override
	public void initialize(IsProductRequestValid constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	@Transactional
	public boolean isValid(Object[] value, ConstraintValidatorContext context) {
		var request = (ProductRequestDto) value[0];
		var res = true;
		if (!StringUtils.hasText(request.name())) {
			buildConstraintValidation(context, "Product name is required");
			res = false;
		} else if (productRepository.existsByName(request.name())) {
			buildConstraintValidation(context, "Product name already exists");
			res = false;
		}
		if (request.price() == null) {
			buildConstraintValidation(context, "Product price is required");
			res = false;
		} else if (request.price() < 0) {
			buildConstraintValidation(context, "Product price cannot be negative");
			res = false;
		}
		return res;
	}

	private void buildConstraintValidation(ConstraintValidatorContext context, String invalidRole) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(invalidRole).addConstraintViolation();
	}
}
