package com.hubs.ing.assignment.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsUserRequestValidValidator.class)
@Documented
public @interface IsUserRequestValid {
	String message() default "Request is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
