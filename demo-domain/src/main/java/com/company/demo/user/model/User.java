package com.company.demo.user.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
	UserId id;
    Email email;
    Name name;
    Surname surname;
    Status status;
}