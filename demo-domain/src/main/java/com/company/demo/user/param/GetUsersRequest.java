package com.company.demo.user.param;

import javax.validation.constraints.Min;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUsersRequest {
	@Min(value = 0)
	Integer pageNumber;
}
