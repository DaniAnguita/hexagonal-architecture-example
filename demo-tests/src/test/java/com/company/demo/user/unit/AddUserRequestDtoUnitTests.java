package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.param.AddUserRequest;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AddUserRequestDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(AddUserRequest.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(AddUserRequest.builder().toString()).isEqualTo(AddUserRequest.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		AddUserRequest dto1 = AddUserRequest.builder().build().withEmail("test@test.com").withName("name").withSurname("surname");
		AddUserRequest dto2 = AddUserRequest.builder().build().withEmail("test@test.com").withName("name").withSurname("surname");
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
