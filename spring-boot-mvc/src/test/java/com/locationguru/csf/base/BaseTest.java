package com.locationguru.csf.base;

import config.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public abstract class BaseTest
{
	private static final Logger logger = LogManager.getLogger(BaseTest.class);

}
