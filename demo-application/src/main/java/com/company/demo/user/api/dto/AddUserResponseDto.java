package com.company.demo.user.api.dto;

import com.company.demo.user.param.AddUserResponse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddUserResponseDto {
	Long id;
    String email;
    String name;
    String surname;
    String status;
    
    public static AddUserResponseDto of(AddUserResponse response) {
    	return AddUserResponseMapper.INSTANCE.domainToDto(response);
    }
}
