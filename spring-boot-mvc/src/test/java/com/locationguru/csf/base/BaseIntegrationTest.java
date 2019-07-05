package com.locationguru.csf.base;

import config.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest
{
	private static final Logger logger = LogManager.getLogger(BaseIntegrationTest.class);

}
