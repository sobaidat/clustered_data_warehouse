package com.warehouse.clustereddata.validator;

import org.springframework.util.StringUtils;

import com.warehouse.clustereddata.util.CurrencyCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.hasLength(value) && CurrencyCode.hasCurrencyCode(value);
	}
}