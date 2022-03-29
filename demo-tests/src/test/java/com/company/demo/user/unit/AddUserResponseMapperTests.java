package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.api.dto.UserDtoMapper;

public class AddUserResponseMapperTests {

	@UnitTest
	void testDomainToDtoNull() {
		assertThat(UserDtoMapper.INSTANCE.domainToDto(null)).isEqualTo(null);
	}

}
