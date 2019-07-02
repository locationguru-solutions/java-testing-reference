package com.locationguru.automation.web.rest.controller;

import java.util.Date;
import java.util.List;

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
	public ResponseEntity<Response<Date>> get()
	{
		return ResponseEntity.ok(Response.ok(List.of(new Date(), new Date(), new Date())));
	}

}
