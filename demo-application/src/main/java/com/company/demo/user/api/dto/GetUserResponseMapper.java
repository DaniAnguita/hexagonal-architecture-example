package com.company.demo.user.api.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.company.demo.user.param.GetUserResponse;

@Mapper
public interface GetUserResponseMapper {
	GetUserResponseMapper INSTANCE = Mappers.getMapper(GetUserResponseMapper.class);

	GetUserResponseDto domainToDto(GetUserResponse response);
	
	GetUserResponse dtoToDomain(GetUserResponseDto dto);
}
