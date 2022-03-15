package com.company.demo.user.param;

import com.company.demo.common.model.Pagination;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class GetUsersResponse {
	Pagination<UserList> usersPagination;
}
