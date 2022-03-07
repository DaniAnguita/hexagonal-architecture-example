package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.data.Status;
import com.company.demo.user.dto.GetUserResponseDto;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUserResponseDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUserResponseDto.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(GetUserResponseDto.builder().toString()).isEqualTo(GetUserResponseDto.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		GetUserResponseDto dto1 = GetUserResponseDto.builder().email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		GetUserResponseDto dto2 = GetUserResponseDto.builder().email("test@test.com").name("name").surname("surname").status(Status.ACTIVE).build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
