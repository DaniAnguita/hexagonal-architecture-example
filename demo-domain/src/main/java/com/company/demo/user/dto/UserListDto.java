package com.company.demo.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserListDto {
	Long id;
    String email;
}
