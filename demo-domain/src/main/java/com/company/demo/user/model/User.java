package com.company.demo.user.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
public class User {
	UserId id;
    Email email;
    Name name;
    Surname surname;
    Status status;
}