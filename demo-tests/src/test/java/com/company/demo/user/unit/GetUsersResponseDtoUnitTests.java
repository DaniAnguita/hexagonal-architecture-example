package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.dto.GetUsersResponseDto;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUsersResponseDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUsersResponseDto.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(GetUsersResponseDto.builder().toString()).isEqualTo(GetUsersResponseDto.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		GetUsersResponseDto dto1 = GetUsersResponseDto.builder().build();
		GetUsersResponseDto dto2 = GetUsersResponseDto.builder().build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
