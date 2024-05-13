package com.warehouse.clustereddata.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AmountValidator.class)
public @interface ValidAmount {
	double minAmount() default 0.0;
	double maxAmount() default 100.0;
	String message() default "Amount must be positive";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
