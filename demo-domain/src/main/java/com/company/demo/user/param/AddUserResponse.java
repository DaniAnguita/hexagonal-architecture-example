package com.company.demo.user.param;

import com.company.demo.user.model.Status;
import com.company.demo.user.model.User;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddUserResponse {
	Long id;
    String email;
    String name;
    String surname;
    Status status;
    
    public static AddUserResponse of(User user) {
    	return AddUserResponse.builder()
				.id(user.getId().getValue())
				.email(user.getEmail().getValue())
				.name(user.getName().getValue())
				.surname(user.getSurname().getValue())
				.status(user.getStatus())
				.build();
    }
}
