package com.company.demo.user.api.dto;

import com.company.demo.common.model.Pagination;
import com.company.demo.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class GetUsersResponseDto {
	Pagination<UserListDto> usersPagination;
    
    public static GetUsersResponseDto of(Pagination<User> users) {
    	Pagination<UserListDto> usersPagination = users.map(u -> UserListDto.builder()
						.id(u.getId().getValue())
						.email(u.getEmail().getValue())
						.build());
    	
    	return new GetUsersResponseDto(usersPagination);
    }
}
