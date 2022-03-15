package com.company.demo.user.param;

import com.company.demo.user.model.Status;
import com.company.demo.user.model.User;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUserResponse {
	String email;
    String name;
    String surname;
    Status status;
    
    public static GetUserResponse of(User user) {
    	return GetUserResponse.builder()
				.email(user.getEmail().getValue())
				.name(user.getName().getValue())
				.surname(user.getSurname().getValue())
				.status(user.getStatus())
				.build();
    }
}
