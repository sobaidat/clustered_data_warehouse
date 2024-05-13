package com.warehouse.clustereddata.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<ValidAmount, Double> {
	
	double minAmount;
	double maxAmount;
	
    @Override
    public void initialize(ValidAmount constraintAnnotation) {
    	this.minAmount = constraintAnnotation.minAmount();
    	this.maxAmount = constraintAnnotation.maxAmount();    	
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= minAmount && value <= maxAmount;
    }
}