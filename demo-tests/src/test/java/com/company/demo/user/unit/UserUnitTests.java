package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.data.Status;
import com.company.demo.user.data.User;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UserUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(User.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(User.builder().toString()).isEqualTo(User.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		User user1 = User.builder().id(1l).email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		User user2 = User.builder().id(1l).email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		assertThat(user1.toString()).isEqualTo(user2.toString());
	}
	
}
