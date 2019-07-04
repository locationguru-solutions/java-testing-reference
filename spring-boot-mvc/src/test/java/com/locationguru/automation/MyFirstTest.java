package com.locationguru.automation;

import org.junit.jupiter.api.*;

public class MyFirstTest
{
	@Test
	@DisplayName("Sum of integers")
	void testIntegerAddition()
	{
		final int sum = 2 + 2;

		Assertions.assertEquals(4, sum, "Expected sum of 2 + 2 is 4");
	}
}


