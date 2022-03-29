package com.company.demo.user.model;

import javax.validation.constraints.NotNull;

import com.company.demo.common.model.DomainValueObject;
import com.company.demo.user.exception.InvalidUserIdException;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
public class UserId extends DomainValueObject {
	@NotNull
	Long value;
	
	public UserId(Long value) {
		this.value = value;
		validateSelf(InvalidUserIdException::new);
	}
}
