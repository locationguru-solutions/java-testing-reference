package com.locationguru.automation.user;

import java.util.List;

import com.locationguru.automation.model.User;
import com.locationguru.automation.service.UserService;
import com.locationguru.csf.base.AbstractControllerTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
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
public class UserControllerTest
		extends AbstractControllerTest
{
	private static final Logger logger = LogManager.getLogger(UserControllerTest.class);

	private final MockMvc mvc;

	@MockBean
	private UserService userService;

	@Autowired
	public UserControllerTest(final MockMvc mvc)
	{
		this.mvc = mvc;
	}

	@BeforeAll
	public static void initializeAll()
	{

	}

	@BeforeEach
	public void initialize()
	{
		// this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	@DisplayName("User creation")
	public void testCreateUser() throws Exception
	{
		logger.info("User creation successful ..");

		final User user = new User();

		user.setIdentity("neo");
		user.setFirstName("Thomas");
		user.setLastName("Anderson");

		Mockito.when(userService.findAll()).thenReturn(List.of(user));

		mvc.perform(MockMvcRequestBuilders.get("/users"))
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andExpect(content().contentType("application/json"))
		   .andExpect(jsonPath("$.results[0].identity").value("neo"));
	}

	@AfterEach
	public void destroy()
	{

	}

	@AfterAll
	public static void destroyAll()
	{

	}

}
