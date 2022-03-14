package com.company.demo.user.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserListDto {
	Long id;
    String email;
}
