package com.locationguru.automation.user;

import java.util.UUID;

import com.locationguru.automation.model.User;
import com.locationguru.automation.service.UserService;
import com.locationguru.automation.web.rest.controller.UserController;
import com.locationguru.csf.base.AbstractControllerTest;
import com.locationguru.csf.web.mvc.EntityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
public class UserControllerTest
		extends AbstractControllerTest<User, User, User, UUID>
{
	private static final Logger logger = LogManager.getLogger(UserControllerTest.class);

	private final UserGenerator userGenerator;

	@MockBean
	private UserService userService;

	@Autowired
	public UserControllerTest(final MockMvc mvc, final UserGenerator userGenerator)
	{
		super(mvc, UserController.class, User::getUid);
		this.userGenerator = userGenerator;
	}

	@Override
	protected void validateEntity(final ResultActions result, final String prefix, final User user) throws Exception
	{
		result.andExpect(jsonPath(prefix + ".identity").value(user.getIdentity()))
			  .andExpect(jsonPath(prefix + ".firstName").value(user.getFirstName()))
			  .andExpect(jsonPath(prefix + ".lastName").value(user.getLastName()));
	}

	@Override
	protected User newEntity()
	{
		return userGenerator.newEntity();
	}

	@Override
	protected EntityService<User, User, UUID> getEntityService()
	{
		return userService;
	}
}
