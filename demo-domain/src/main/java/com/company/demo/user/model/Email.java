package com.company.demo.user.model;

import javax.validation.constraints.NotEmpty;

import com.company.demo.common.model.DomainModel;
import com.company.demo.user.exception.InvalidEmailException;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class Email extends DomainModel<Email>{
	@NotEmpty
	@javax.validation.constraints.Email
	String value;
	
	public Email(String value) {
		this.value = value;
		validateSelf(InvalidEmailException::new);
	}
}
