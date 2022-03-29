package com.company.demo.user.model;

import javax.validation.constraints.NotNull;

import com.company.demo.common.model.DomainModel;
import com.company.demo.user.exception.InvalidNameException;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
public class Name extends DomainModel<Name> {
	@NotNull
	String value;
	
	public Name(String value) {
		this.value = value;
		validateSelf(InvalidNameException::new);
	}
}
