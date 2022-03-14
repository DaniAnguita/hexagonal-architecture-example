package com.company.demo.user.unit;

import com.company.demo.UnitTest;
import com.company.demo.user.param.GetUserRequest;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUserRequestDtoUnitTests {
	
	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUserRequest.class).verify();
	}
	
}
