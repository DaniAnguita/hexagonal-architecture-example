package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.model.Status;
import com.company.demo.user.param.GetUserResponse;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUserResponseDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUserResponse.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(GetUserResponse.builder().toString()).isEqualTo(GetUserResponse.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		GetUserResponse dto1 = GetUserResponse.builder().email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		GetUserResponse dto2 = GetUserResponse.builder().email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
