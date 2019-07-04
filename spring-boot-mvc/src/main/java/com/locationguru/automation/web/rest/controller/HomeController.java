package com.locationguru.automation.web.rest.controller;

import com.locationguru.automation.model.User;
import com.locationguru.csf.web.rest.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@GetMapping(value = "/home", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Response<User>> get()
	{
		final User user = new User();

		user.setIdentity("neo");
		user.setFirstName("Thomas");
		user.setLastName("Anderson");

		return ResponseEntity.ok(Response.ok(user));
	}

}
