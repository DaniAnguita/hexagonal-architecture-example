package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.dto.AddUserRequestDto;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AddUserRequestDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(AddUserRequestDto.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(AddUserRequestDto.builder().toString()).isEqualTo(AddUserRequestDto.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		AddUserRequestDto dto1 = AddUserRequestDto.builder().build().withEmail("test@test.com").withName("name").withSurname("surname");
		AddUserRequestDto dto2 = AddUserRequestDto.builder().build().withEmail("test@test.com").withName("name").withSurname("surname");
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
