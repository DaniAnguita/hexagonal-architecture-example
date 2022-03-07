package com.company.demo.user.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.UnitTest;
import com.company.demo.user.dto.GetUserRequestDto;

import nl.jqno.equalsverifier.EqualsVerifier;

public class GetUserRequestDtoUnitTests {
	
	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(GetUserRequestDto.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(GetUserRequestDto.builder().toString()).isEqualTo(GetUserRequestDto.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		GetUserRequestDto dto1 = GetUserRequestDto.builder().id(1l).build();
		GetUserRequestDto dto2 = GetUserRequestDto.builder().id(1l).build();
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}
	
}
