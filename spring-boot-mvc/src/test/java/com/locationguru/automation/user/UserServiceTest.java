package com.locationguru.automation.user;

import java.util.List;

import com.locationguru.automation.model.User;
import com.locationguru.automation.repository.UserRepository;
import com.locationguru.automation.service.UserService;
import com.locationguru.csf.base.AbstractEntityServiceTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureMockMvc
public class UserServiceTest
		extends AbstractEntityServiceTest
{
	private static final Logger logger = LogManager.getLogger(UserServiceTest.class);

	private final UserService userService;
	private final UserGenerator userGenerator;

	@MockBean
	private UserRepository repository;

	@Autowired
	public UserServiceTest(final UserService userService, final UserGenerator userGenerator)
	{
		this.userService = userService;
		this.userGenerator = userGenerator;
	}

	@Test
	public void testFindAllUsers()
	{
		final User user1 = userGenerator.newEntity();
		final User user2 = userGenerator.newEntity();

		Mockito.when(repository.findAll(1L)).thenReturn(List.of(user1, user2));

		final List<User> users = userService.findAll();

		Assertions.assertNotNull(users);
		Assertions.assertEquals(2, users.size(), "Received unexpected number of users ");

	}

}
