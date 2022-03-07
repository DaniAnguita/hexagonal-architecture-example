package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.jpa.UserEntityMapper;

public class UserEntityMapperImplTests {

	@UnitTest
	void testUserToEntityNull() {
		assertThat(UserEntityMapper.INSTANCE.userToEntity(null)).isEqualTo(null);
	}

	@UnitTest
	void testEntityToUserNull() {
		assertThat(UserEntityMapper.INSTANCE.entityToUser(null)).isEqualTo(null);
	}
	
}
