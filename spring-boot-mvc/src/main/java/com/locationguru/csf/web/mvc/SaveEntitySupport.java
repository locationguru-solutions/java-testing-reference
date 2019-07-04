package com.locationguru.csf.web.mvc;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.locationguru.csf.web.rest.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SaveEntitySupport<Type, Id extends Serializable, RequestType, ResponseType>
		extends ValidationErrorProcessorSupport, EntityServiceSupport<Type, RequestType, Id>, EntityTransformSupport<Type, ResponseType>
{
	static final Logger logger = LogManager.getLogger(SaveEntitySupport.class);

	@PostMapping(path = { "/", "" }, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public default ResponseEntity<Response<ResponseType>> save(final HttpServletRequest request, HttpServletResponse response, final @RequestBody(required = false) @Valid RequestType dto, final BindingResult bindingResult)
	{
		if (dto == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return ResponseEntity.badRequest().body(Response.badRequest("Empty request body"));
		}

		if (bindingResult.hasErrors()) { return processValidationErrors(bindingResult); }

		final EntityService<Type, RequestType, Id> entityService = getEntityService();

		final Type type = entityService.createEntity(dto);
		final ResponseType result = this.transform(type);

		return ResponseEntity.ok().body(Response.ok(result));
	}

}
