package com.company.demo.user.api.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.company.demo.user.param.AddUserResponse;

@Mapper
public interface AddUserResponseMapper {
	AddUserResponseMapper INSTANCE = Mappers.getMapper(AddUserResponseMapper.class);

	AddUserResponseDto domainToDto(AddUserResponse response);
}
