package com.company.demo.user.api.dto;

import com.company.demo.user.model.User;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
	Long id;
    String email;
    String name;
    String surname;
    String status;
    
    public static UserDto of(User user) {
    	return UserDtoMapper.INSTANCE.domainToDto(user);
    }
}
