package com.warehouse.clustereddata.validator;

import org.springframework.util.StringUtils;

import com.warehouse.clustereddata.util.DateTimeUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateTimeValidator implements ConstraintValidator<ValidDateTime, String> {
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.hasLength(value) && DateTimeUtil.validDateTime(value);
	}
}