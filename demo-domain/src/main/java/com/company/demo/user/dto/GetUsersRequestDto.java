package com.company.demo.user.dto;

import javax.validation.constraints.Min;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUsersRequestDto {
	@Min(value = 0)
	Integer pageNumber;
}
