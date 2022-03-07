package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.data.Status;
import com.company.demo.user.dto.AddUserResponseDto;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AddUserResponseDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(AddUserResponseDto.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(AddUserResponseDto.builder().toString()).isEqualTo(AddUserResponseDto.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		AddUserResponseDto dto1 = AddUserResponseDto.builder().id(1l).email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		AddUserResponseDto dto2 = AddUserResponseDto.builder().id(1l).email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
