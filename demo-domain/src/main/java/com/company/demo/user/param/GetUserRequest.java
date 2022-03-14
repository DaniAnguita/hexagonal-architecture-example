package com.company.demo.user.param;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class GetUserRequest {
	@Min(value = 1)
	Long id;
}
