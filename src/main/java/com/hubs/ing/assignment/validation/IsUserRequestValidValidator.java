package com.hubs.ing.assignment.validation;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hubs.ing.assignment.database.entities.StoreUserRole;
import com.hubs.ing.assignment.models.request.UserRequestDto;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import lombok.RequiredArgsConstructor;

@Service
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
@RequiredArgsConstructor
public class IsUserRequestValidValidator implements ConstraintValidator<IsUserRequestValid, Object[]> {

	@Override
	public void initialize(IsUserRequestValid constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	@Transactional
	public boolean isValid(Object[] value, ConstraintValidatorContext context) {
		var request = (UserRequestDto) value[0];
		boolean res = Optional.ofNullable(request.roles())
				.stream()
				.flatMap(roles -> Arrays.stream(roles.split(",")))
				.peek(role -> {
					try {
						StoreUserRole.valueOf(role);
					} catch (IllegalArgumentException | NullPointerException e) {
						buildConstraintValidation(context, "Invalid roles");
					}
				})
				.map(role -> true)
				.findAny()
				.orElse(false);
		if (!StringUtils.hasText(request.username())) {
			buildConstraintValidation(context, "Username is required");
			res = false;
		}
		if (!StringUtils.hasText(request.password())) {
			buildConstraintValidation(context, "Password is required");
			res = false;
		}
		return res;
	}

	private void buildConstraintValidation(ConstraintValidatorContext context, String invalidRole) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(invalidRole).addConstraintViolation();
	}
}
