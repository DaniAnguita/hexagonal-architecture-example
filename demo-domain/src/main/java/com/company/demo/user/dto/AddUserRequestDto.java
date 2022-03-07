package com.company.demo.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class AddUserRequestDto {
	@Email
	@NotEmpty
    String email;
    
	@NotEmpty
	@Size(min = 3, max = 40)
    String name;
    
	@NotEmpty
	@Size(min = 3, max = 80)
    String surname;
}
