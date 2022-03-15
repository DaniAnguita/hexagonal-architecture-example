package com.company.demo.user.api.dto;

import com.company.demo.user.param.AddUserRequest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddUserRequestDto {
	String email;
	String name;
	String surname;
	
	public AddUserRequest toDomain() {
		return AddUserRequest.builder()
				.email(email)
				.name(name)
				.surname(surname)
				.build();
	}
}
