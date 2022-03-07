package com.company.demo.user.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.company.demo.user.data.User;

@Mapper
public interface UserEntityMapper {
	UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

	UserEntity userToEntity(User user);
	
	User entityToUser(UserEntity entity);
}
