package com.company.demo.common.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.company.demo.common.exception.InvalidPageNumberException;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
public class PageNumber extends DomainValueObject {
	@NotNull
	@Min(1)
	Integer value;
	
	public PageNumber(Integer value) {
		this.value = value;
		validateSelf(InvalidPageNumberException::new);
	}

}
