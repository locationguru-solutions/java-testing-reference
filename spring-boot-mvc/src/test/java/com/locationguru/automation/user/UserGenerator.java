package com.locationguru.automation.user;

import java.util.UUID;

import com.locationguru.automation.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserGenerator
{
	private static final Logger logger = LogManager.getLogger(UserGenerator.class);

	protected User newEntity()
	{
		final User user = new User();

		user.setUid(UUID.randomUUID());
		user.setIdentity("neo");
		user.setFirstName("Thomas");
		user.setLastName("Anderson");

		return user;
	}
}
