package com.company.demo.user.model;

import javax.validation.constraints.NotNull;

import com.company.demo.common.model.DomainValueObject;
import com.company.demo.user.exception.InvalidSurnameException;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
public class Surname extends DomainValueObject {
	@NotNull
	String value;
	
	public Surname(String value) {
		this.value = value;
		validateSelf(InvalidSurnameException::new);
	}
}
