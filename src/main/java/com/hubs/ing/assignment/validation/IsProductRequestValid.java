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
@Constraint(validatedBy = IsProductRequestValidValidator.class)
@Documented
public @interface IsProductRequestValid {
	String message() default "Invalid product request";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}