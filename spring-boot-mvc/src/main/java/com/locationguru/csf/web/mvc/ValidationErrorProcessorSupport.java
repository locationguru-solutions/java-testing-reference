package com.locationguru.csf.web.mvc;

import java.util.List;

import com.locationguru.csf.web.rest.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;

public interface ValidationErrorProcessorSupport
{
	static final Logger logger = LogManager.getLogger(ValidationErrorProcessorSupport.class);

	public default <ResponseType> ResponseEntity<Response<ResponseType>> processValidationErrors(final BindingResult result)
	{
		final List<FieldError> fieldErrors = result.getFieldErrors();

		for (final FieldError error : fieldErrors)
		{
			final String message = "Field '" + error.getField() + "' " + error.getDefaultMessage();
			logger.info("Validation failure : {}", message);

			return ResponseEntity.badRequest().body(Response.badRequest(message));
		}

		final List<ObjectError> globalErrors = result.getGlobalErrors();

		for (final ObjectError error : globalErrors)
		{
			final String message = "Field '" + error.getObjectName() + "' " + error.getDefaultMessage();
			logger.info("Validation failure : {}", message);

			return ResponseEntity.badRequest().body(Response.badRequest(message));
		}

		// This should never get called
		throw new RuntimeException("Binding result has errors but can't be iterated ..");
	}

}
