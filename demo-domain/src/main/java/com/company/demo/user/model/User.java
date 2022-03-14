package com.company.demo.user.model;

import com.company.demo.common.model.Email;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
	Long id;
    Email email;
    String name;
    String surname;
    Status status;
}