package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.model.Email;
import com.company.demo.user.model.Name;
import com.company.demo.user.model.Status;
import com.company.demo.user.model.Surname;
import com.company.demo.user.model.User;
import com.company.demo.user.model.UserId;

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
		User user1 = User.builder()
				.id(new UserId(1l))
				.email(new Email("test@test.com"))
				.name(new Name("name"))
				.surname(new Surname("surname"))
				.status(Status.ACTIVE)
				.build();
		
		User user2 = User.builder()
				.id(new UserId(1l))
				.email(new Email("test@test.com"))
				.name(new Name("name"))
				.surname(new Surname("surname"))
				.status(Status.ACTIVE)
				.build();
		
		assertThat(user1.toString()).isEqualTo(user2.toString());
	}
	
}
