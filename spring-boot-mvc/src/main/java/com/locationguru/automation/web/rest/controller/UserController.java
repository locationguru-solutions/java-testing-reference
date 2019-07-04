package com.locationguru.automation.web.rest.controller;

import java.util.UUID;

import com.locationguru.automation.model.User;
import com.locationguru.automation.service.UserService;
import com.locationguru.csf.web.mvc.AbstractController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController
		extends AbstractController<User, UUID, User, User>
{
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	protected UserController(final UserService userService)
	{
		super(userService);
	}

	@Override
	public User transform(final User user)
	{
		return user;
	}
}