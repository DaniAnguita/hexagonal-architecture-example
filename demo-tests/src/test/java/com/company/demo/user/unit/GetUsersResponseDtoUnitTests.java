package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.param.GetUsersResponse;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUsersResponseDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUsersResponse.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(GetUsersResponse.builder().toString()).isEqualTo(GetUsersResponse.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		GetUsersResponse dto1 = GetUsersResponse.builder().build();
		GetUsersResponse dto2 = GetUsersResponse.builder().build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
