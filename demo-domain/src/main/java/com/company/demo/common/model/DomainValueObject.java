package com.company.demo.common.model;

import java.util.Set;
import java.util.function.Supplier;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class DomainValueObject {

	protected void validateSelf(Supplier<? extends RuntimeException> exceptionSupplier) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<DomainValueObject>> violations = validator.validate(this);
		if (!violations.isEmpty()) {
			throw exceptionSupplier.get();
		}
	}
	
}
