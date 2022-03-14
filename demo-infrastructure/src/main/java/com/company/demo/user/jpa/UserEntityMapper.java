package com.company.demo.user.jpa;

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
public interface UserEntityMapper {
	UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

	@Mapping(target = "id", qualifiedByName = "idToLong")
	@Mapping(target = "email", qualifiedByName = "emailToString")
	@Mapping(target = "name", qualifiedByName = "nameToString")
	@Mapping(target = "surname", qualifiedByName = "surnameToString")
	UserEntity domainToEntity(User user);
	
	@Mapping(target = "id", qualifiedByName = "longToId")
	@Mapping(target = "email", qualifiedByName = "stringToEmail")
	@Mapping(target = "name", qualifiedByName = "stringToName")
	@Mapping(target = "surname", qualifiedByName = "stringToSurname")
	User entityToDomain(UserEntity entity);
	
	@Named("idToLong")
	default Long idToLong(UserId id) {
		return id != null ? id.getValue() : null;
	}
	
	@Named("longToId")
	default UserId longToId(Long idValue) {
		return idValue != null ? new UserId(idValue) : null;
	}
	
	@Named("emailToString")
	default String emailToString(Email email) {
		return email.getValue();
	}
	
	@Named("stringToEmail")
	default Email stringToEmail(String emailValue) {
		return new Email(emailValue);
	}
	
	@Named("nameToString")
	default String nameToString(Name name) {
		return name.getValue();
	}
	
	@Named("stringToName")
	default Name stringToName(String nameValue) {
		return new Name(nameValue);
	}
	
	@Named("surnameToString")
	default String surnameToString(Surname surname) {
		return surname.getValue();
	}
	
	@Named("stringToSurname")
	default Surname stringToSurname(String surnameValue) {
		return new Surname(surnameValue);
	}
}
