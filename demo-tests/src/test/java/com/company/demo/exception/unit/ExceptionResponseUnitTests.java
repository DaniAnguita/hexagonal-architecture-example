package com.company.demo.exception.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import com.company.demo.UnitTest;
import com.company.demo.common.model.ExceptionResponse;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ExceptionResponseUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(ExceptionResponse.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(ExceptionResponse.builder().toString()).isEqualTo(ExceptionResponse.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		ExceptionResponse exception1 = ExceptionResponse.builder().errors(Map.of("1", "2")).build();
		ExceptionResponse exception2 = ExceptionResponse.builder().errors(Map.of("1", "2")).build();
		assertThat(exception1.toString()).isEqualTo(exception2.toString());
	}
	
}
