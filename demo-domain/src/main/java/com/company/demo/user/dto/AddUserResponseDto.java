package com.company.demo.user.dto;

import com.company.demo.user.data.Status;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddUserResponseDto {
	Long id;
    String email;
    String name;
    String surname;
    Status status;
}
