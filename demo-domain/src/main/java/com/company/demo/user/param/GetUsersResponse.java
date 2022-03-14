package com.company.demo.user.param;

import com.company.demo.common.model.Pagination;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUsersResponse {
	Pagination<UserList> usersPagination;
}
