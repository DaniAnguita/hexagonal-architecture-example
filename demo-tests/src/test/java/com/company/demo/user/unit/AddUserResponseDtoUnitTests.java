package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.model.Status;
import com.company.demo.user.param.AddUserResponse;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AddUserResponseDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(AddUserResponse.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(AddUserResponse.builder().toString()).isEqualTo(AddUserResponse.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		AddUserResponse dto1 = AddUserResponse.builder().id(1l).email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		AddUserResponse dto2 = AddUserResponse.builder().id(1l).email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
