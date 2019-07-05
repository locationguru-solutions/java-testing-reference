package com.locationguru.automation.user;

import java.util.List;

import com.locationguru.automation.model.User;
import com.locationguru.automation.service.UserService;
import com.locationguru.csf.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
public class UserControllerTestOld
		extends BaseTest
{
	private static final Logger logger = LogManager.getLogger(UserControllerTestOld.class);

	private final MockMvc mvc;

	@MockBean
	private UserService userService;

	@Autowired
	public UserControllerTestOld(final MockMvc mvc)
	{
		this.mvc = mvc;
	}

	@Test
	@DisplayName("Get all users")
	public void testFindAllUsers() throws Exception
	{
		final User user = new User();

		user.setIdentity("neo");
		user.setFirstName("Thomas");
		user.setLastName("Anderson");

		Mockito.when(userService.findAll()).thenReturn(List.of(user));

		mvc.perform(MockMvcRequestBuilders.get("/users"))
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andExpect(content().contentType("application/json"))
		   .andExpect(jsonPath("$.results[0].identity").value(user.getIdentity()))
		   .andExpect(jsonPath("$.results[0].firstName").value(user.getFirstName()))
		   .andExpect(jsonPath("$.results[0].lastName").value(user.getLastName()));
	}
}
