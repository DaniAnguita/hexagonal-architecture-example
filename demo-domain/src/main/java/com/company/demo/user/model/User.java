package com.company.demo.user.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.company.demo.common.model.DomainEntity;
import com.company.demo.user.exception.UserValidationCanBeAddedException;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class User extends DomainEntity {
	
	@Null(groups = UserValidationCanBeAdded.class)
	UserId id;
	
	@NotNull(groups = UserValidationCanBeAdded.class)
    Email email;
	
	@NotNull(groups = UserValidationCanBeAdded.class)
    Name name;
	
	@NotNull(groups = UserValidationCanBeAdded.class)
    Surname surname;
	
	@Null(groups = UserValidationCanBeAdded.class)
    Status status;
    
    public void validateUserCanBeAdded() {
    	validateSelf(UserValidationCanBeAddedException::new, UserValidationCanBeAdded.class);
    }
}