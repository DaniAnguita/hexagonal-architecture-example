package com.company.demo.user.dto;

import com.company.demo.common.data.Pagination;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUsersResponseDto {
	Pagination<UserListDto> usersPagination;
}
