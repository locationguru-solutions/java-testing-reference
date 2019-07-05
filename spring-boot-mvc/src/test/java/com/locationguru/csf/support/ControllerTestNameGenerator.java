package com.locationguru.csf.support;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import com.locationguru.csf.base.AbstractControllerTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayNameGenerator;

public class ControllerTestNameGenerator
		implements DisplayNameGenerator
{
	private static final Logger logger = LogManager.getLogger(ControllerTestNameGenerator.class);

	@Override
	public String generateDisplayNameForClass(final Class<?> clazz)
	{
		return clazz.getSimpleName();
	}

	@Override
	public String generateDisplayNameForNestedClass(final Class<?> clazz)
	{
		return clazz.getSimpleName();
	}

	@Override
	public String generateDisplayNameForMethod(final Class<?> clazz, final Method method)
	{
		final String methodName = method.getName();

		if (method.getDeclaringClass() == AbstractControllerTest.class)
		{
			final ParameterizedType thisClass = (ParameterizedType) clazz.getGenericSuperclass();
			final Class<?> type = (Class<?>) thisClass.getActualTypeArguments()[0];

			switch (methodName)
			{
				case "testEntityList":
				{
					return "Get all " + type.getSimpleName().toLowerCase() + "s";
				}

				case "testEntityGet":
				{
					return "Get " + type.getSimpleName().toLowerCase() + " by id";
				}

				case "testEntityCreation":
				{
					return "Create " + type.getSimpleName().toLowerCase();
				}

				case "testEntityDeletion":
				{
					return "Delete " + type.getSimpleName().toLowerCase();
				}
			}
		}

		return methodName;
	}
}
