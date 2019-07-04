package com.locationguru.automation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyFirstTest
{
	@Test
	void testAddition()
	{
		final int sum = 2 + 2;

		Assertions.assertEquals(4, sum, "Expected sum of 2 + 2 is 4");
	}
}


