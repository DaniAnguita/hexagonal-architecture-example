package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.api.dto.UserDto;
import com.company.demo.user.api.dto.UserDtoMapper;
import com.company.demo.user.model.User;

public class UserDtoMapperUnitTests {
	
	@UnitTest
	void testDomainToNull() {
		assertThat(UserDtoMapper.INSTANCE.domainToDto(null)).isEqualTo(null);
	}
	
	@UnitTest
	void testDomainToDtoFieldsNull() {
		UserDto dtoMapped = UserDto.of(User.builder().build());
		assertThat(dtoMapped.getId()).isNull();
		assertThat(dtoMapped.getEmail()).isNull();
		assertThat(dtoMapped.getName()).isNull();
		assertThat(dtoMapped.getSurname()).isNull();		
	}

}
