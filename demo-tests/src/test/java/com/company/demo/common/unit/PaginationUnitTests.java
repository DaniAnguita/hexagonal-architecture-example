package com.company.demo.common.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.company.demo.UnitTest;
import com.company.demo.common.data.Pagination;

import nl.jqno.equalsverifier.EqualsVerifier;

public class PaginationUnitTests {

	@UnitTest
	void equalsHashCode() {
		EqualsVerifier.forClass(Pagination.class).verify();
	}
	
	@UnitTest
	void builderToString() {
		assertThat(Pagination.builder().toString()).isEqualTo(Pagination.builder().toString());
	}
	
	@UnitTest
	void objectToString() {
		Pagination<String> pagination1 = Pagination.<String>builder().content(List.of("1")).build();
		Pagination<String> pagination2 = Pagination.<String>builder().content(List.of("1")).build();
		assertThat(pagination1.toString()).isEqualTo(pagination2.toString());
	}
	
}
