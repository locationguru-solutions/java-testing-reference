package com.locationguru.csf.base;

import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationguru.csf.support.ControllerTestNameGenerator;
import com.locationguru.csf.web.mvc.EntityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@DisplayNameGeneration(value = ControllerTestNameGenerator.class)
public abstract class AbstractControllerTest<Type, Request, Response, Identity>
		extends BaseTest
{
	private static final Logger logger = LogManager.getLogger(AbstractControllerTest.class);

	private final String baseUrl;
	private final Function<Type, Identity> identityFunction;

	protected final MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	public AbstractControllerTest(final MockMvc mvc, final Class controllerClass, final Function<Type, Identity> identityFunction)
	{
		this.mvc = mvc;
		this.baseUrl = ((RequestMapping) controllerClass.getAnnotation(RequestMapping.class)).value()[0];
		this.identityFunction = identityFunction;
	}

	@Test
	public void testEntityList() throws Exception
	{
		final Type entity = newEntity();
		Mockito.when(getEntityService().findAll()).thenReturn(List.of(entity));

		final ResultActions result = mvc.perform(MockMvcRequestBuilders.get(baseUrl))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andExpect(content().contentType("application/json"));

		this.validateEntity(result, 0, entity);
	}

	@Test
	public void testEntityGet() throws Exception
	{
		final Type entity = newEntity();
		final Identity identity = this.identityFunction.apply(entity);

		Mockito.when(getEntityService().findById(identity)).thenReturn(entity);

		final ResultActions result = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + identity))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andExpect(content().contentType("application/json"));

		this.validateEntity(result, "$.result", entity);
	}

	public void testEntityCreation() throws Exception
	{
		final Type entity = newEntity();

		// Mockito.when(getEntityService().findById(identity)).thenReturn(entity);

		final ResultActions result = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
																	   .contentType(MediaType.APPLICATION_JSON)
																	   .content(objectMapper.writeValueAsString(entity)))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andExpect(content().contentType("application/json"));

		this.validateEntity(result, "$.result", entity);
	}

	@Test
	public void testEntityDeletion() throws Exception
	{
		final Type entity = newEntity();
		final Identity identity = this.identityFunction.apply(entity);

		Mockito.when(getEntityService().findById(identity)).thenReturn(entity);

		mvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/" + identity))
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andExpect(content().contentType("application/json"));
	}


	protected abstract Type newEntity();

	protected abstract void validateEntity(final ResultActions result, final String prefix, final Type entity) throws Exception;

	protected void validateEntity(final ResultActions result, final int index, final Type entity) throws Exception
	{
		this.validateEntity(result, "$.results[" + 0 + "]", entity);
	}

	protected abstract EntityService<Type, Request, Identity> getEntityService();
}
