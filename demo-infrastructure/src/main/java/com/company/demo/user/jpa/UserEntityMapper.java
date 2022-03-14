package com.company.demo.user.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.company.demo.common.model.Email;
import com.company.demo.user.model.User;

@Mapper
public interface UserEntityMapper {
	UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

	@Mapping(target = "email", qualifiedByName = "emailToString")
	UserEntity domainToEntity(User user);
	
	@Mapping(target = "email", qualifiedByName = "stringToEmail")
	User entityToDomain(UserEntity entity);
	
	@Named("emailToString")
	default String emailToString(Email email) {
		return email.getValue();
	}
	
	@Named("stringToEmail")
	default Email stringToEmail(String emailValue) {
		return new Email(emailValue);
	}
}
