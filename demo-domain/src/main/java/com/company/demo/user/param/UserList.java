package com.company.demo.user.param;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserList {
	Long id;
    String email;
}
