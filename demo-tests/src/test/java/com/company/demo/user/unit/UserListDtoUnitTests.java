package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.dto.UserListDto;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UserListDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(UserListDto.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(UserListDto.builder().toString()).isEqualTo(UserListDto.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		UserListDto dto1 = UserListDto.builder().id(1l).email("test@test.com").build();
		UserListDto dto2 = UserListDto.builder().id(1l).email("test@test.com").build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
