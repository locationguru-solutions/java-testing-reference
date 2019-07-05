package com.locationguru.automation.user;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationguru.automation.model.User;
import com.locationguru.csf.base.BaseIntegrationTest;
import com.locationguru.csf.web.rest.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

public class UserIntegrationTest
		extends BaseIntegrationTest
{
	private static final Logger logger = LogManager.getLogger(UserIntegrationTest.class);

	final TypeReference<Response<User>> userTypeReference = new TypeReference<>()
	{
	};

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	@LocalServerPort
	private Integer serverPort;
	private String baseUrl;

	@Autowired
	public UserIntegrationTest(final RestTemplateBuilder restTemplateBuilder, final ObjectMapper objectMapper)
	{
		this.objectMapper = objectMapper;
		this.restTemplate = restTemplateBuilder.build();
	}

	@PostConstruct
	protected void initialize()
	{
		this.baseUrl = "http://localhost:" + serverPort + "/api";
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUserCreation() throws Exception
	{
		final User requestBody = new User();

		requestBody.setIdentity("neo");
		requestBody.setFirstName("Thomas");
		requestBody.setLastName("Anderson");

		final LinkedMultiValueMap headers = new LinkedMultiValueMap<>(Map.of(HttpHeaders.CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON_VALUE)));
		final HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

		final ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl + "/users", request, String.class);
		final String responseString = responseEntity.getBody();

		final Response<User> response = objectMapper.readValue(responseString, userTypeReference);

		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected status code");
		Assertions.assertNotNull(response, "Unexpected response body 'null'");

		logger.info("User : {}", response);

		final User user = response.getResult();

		Assertions.assertNotNull(user, "Unexpected result entity 'null'");
		Assertions.assertNotNull(user.getUid(), "Unexpected public identity 'null'");

		Assertions.assertEquals(requestBody.getIdentity(), user.getIdentity(), "User identity doesn't match");
		Assertions.assertEquals(requestBody.getFirstName(), user.getFirstName(), "First name doesn't match");
		Assertions.assertEquals(requestBody.getLastName(), user.getLastName(), "Last name doesn't match");

		logger.info("Response : {}", response);
	}

}
