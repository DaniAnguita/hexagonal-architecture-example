package com.company.demo.user.unit;

import org.junit.jupiter.api.Assertions;

import com.company.demo.UnitTest;
import com.company.demo.user.exception.InvalidNameException;
import com.company.demo.user.model.Name;

public class NameUnitTests {
	
	@UnitTest
	void nameNull() {
		Assertions.assertThrows(InvalidNameException.class, () -> new Name(null));
	}

}
