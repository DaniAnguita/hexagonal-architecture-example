package com.company.demo.user.api.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.company.demo.user.model.Email;
import com.company.demo.user.model.Name;
import com.company.demo.user.model.Surname;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;

@Mapper
public interface UserDtoMapper {
	UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

	@Mapping(target = "id", qualifiedByName = "idToLong")
	@Mapping(target = "email", qualifiedByName = "emailToString")
	@Mapping(target = "name", qualifiedByName = "nameToString")
	@Mapping(target = "surname", qualifiedByName = "surnameToString")
	UserDto domainToDto(User user);
	

	@Named("idToLong")
	default Long idToLong(UserId id) {
		return id != null ? id.getValue() : null;
	}
	
	@Named("emailToString")
	default String emailToString(Email email) {
		return email != null ? email.getValue() : null;
	}
	
	@Named("nameToString")
	default String nameToString(Name name) {
		return name != null ? name.getValue() : null;
	}
	
	@Named("surnameToString")
	default String surnameToString(Surname surname) {
		return surname != null ? surname.getValue() : null;
	}
}
