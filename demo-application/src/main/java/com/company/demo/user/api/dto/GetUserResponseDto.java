package com.company.demo.user.api.dto;

import com.company.demo.user.model.User;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUserResponseDto {
	String email;
    String name;
    String surname;
    String status;
    
    public static GetUserResponseDto of(User user) {
    	return GetUserResponseDto.builder()
    			.email(user.getEmail().getValue())
    			.name(user.getName().getValue())
    			.surname(user.getSurname().getValue())
    			.status(user.getStatus().toString())
    			.build();
    }
}
