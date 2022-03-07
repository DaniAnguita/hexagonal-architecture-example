package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.dto.GetUsersRequestDto;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUsersRequestDtoUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUsersRequestDto.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(GetUsersRequestDto.builder().toString()).isEqualTo(GetUsersRequestDto.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		GetUsersRequestDto dto1 = GetUsersRequestDto.builder().pageNumber(1).build();
		GetUsersRequestDto dto2 = GetUsersRequestDto.builder().pageNumber(1).build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
