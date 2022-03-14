package com.company.demo.user.param;

import com.company.demo.user.model.Status;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddUserResponse {
	Long id;
    String email;
    String name;
    String surname;
    Status status;
}
