package com.company.demo.user.dto;

import javax.validation.constraints.Min;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUserRequestDto {
	@Min(value = 1)
	Long id;
}
