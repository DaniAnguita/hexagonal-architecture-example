package com.company.demo.user.unit;

import org.junit.jupiter.api.Assertions;

import com.company.demo.UnitTest;
import com.company.demo.user.exception.InvalidSurnameException;
import com.company.demo.user.model.Surname;

public class SurnameUnitTests {

	@UnitTest
	void surnameNull() {
		Assertions.assertThrows(InvalidSurnameException.class, () -> new Surname(null));
	}
	
}
