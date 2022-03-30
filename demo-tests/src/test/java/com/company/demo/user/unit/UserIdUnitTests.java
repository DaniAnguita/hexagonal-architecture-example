package com.company.demo.user.unit;

import org.junit.jupiter.api.Assertions;

import com.company.demo.UnitTest;
import com.company.demo.user.exception.InvalidUserIdException;
import com.company.demo.user.model.UserId;

public class UserIdUnitTests {

	@UnitTest
	void emailNull() {
		Assertions.assertThrows(InvalidUserIdException.class, () -> new UserId(null));
	}
	
}
