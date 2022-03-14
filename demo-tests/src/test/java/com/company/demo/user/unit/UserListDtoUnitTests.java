package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.param.UserList;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UserListDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(UserList.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(UserList.builder().toString()).isEqualTo(UserList.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		UserList dto1 = UserList.builder().id(1l).email("test@test.com").build();
		UserList dto2 = UserList.builder().id(1l).email("test@test.com").build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
