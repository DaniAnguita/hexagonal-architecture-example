package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.param.GetUsersRequest;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUsersRequestDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUsersRequest.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(GetUsersRequest.builder().toString()).isEqualTo(GetUsersRequest.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		GetUsersRequest dto1 = GetUsersRequest.builder().pageNumber(1).build();
		GetUsersRequest dto2 = GetUsersRequest.builder().pageNumber(1).build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
