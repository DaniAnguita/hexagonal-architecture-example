package com.company.demo.user.api.dto;

import com.company.demo.user.param.GetUserResponse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUserResponseDto {
	String email;
    String name;
    String surname;
    String status;
    
    public static GetUserResponseDto of(GetUserResponse response) {
    	return GetUserResponseMapper.INSTANCE.domainToDto(response);
    }
}
