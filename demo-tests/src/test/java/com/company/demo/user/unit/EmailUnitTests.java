package com.company.demo.user.unit;

import org.junit.jupiter.api.Assertions;

import com.company.demo.UnitTest;
import com.company.demo.user.exception.InvalidEmailException;
import com.company.demo.user.model.Email;

public class EmailUnitTests {

	@UnitTest
	void emailNull() {
		Assertions.assertThrows(InvalidEmailException.class, () -> new Email(null));
	}
	
}
