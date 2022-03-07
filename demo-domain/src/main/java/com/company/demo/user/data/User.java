package com.company.demo.user.data;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
	Long id;
    String email;
    String name;
    String surname;
    Status status;
}
