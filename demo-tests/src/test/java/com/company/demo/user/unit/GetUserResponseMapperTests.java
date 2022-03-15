package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.api.dto.GetUserResponseMapper;

public class GetUserResponseMapperTests {

	@UnitTest
	void testDomainToDtoNull() {
		assertThat(GetUserResponseMapper.INSTANCE.domainToDto(null)).isEqualTo(null);
	}

}
