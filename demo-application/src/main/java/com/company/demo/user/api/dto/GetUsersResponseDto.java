package com.company.demo.user.api.dto;

import com.company.demo.common.model.Pagination;
import com.company.demo.user.param.GetUsersResponse;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class GetUsersResponseDto {
	Pagination<UserListDto> usersPagination;
    
    public static GetUsersResponseDto of(GetUsersResponse response) {
    	Pagination<UserListDto> usersPagination = response.getUsersPagination().map(u -> UserListDto.builder()
						.id(u.getId())
						.email(u.getEmail())
						.build());
    	
    	return new GetUsersResponseDto(usersPagination);
    }
}
