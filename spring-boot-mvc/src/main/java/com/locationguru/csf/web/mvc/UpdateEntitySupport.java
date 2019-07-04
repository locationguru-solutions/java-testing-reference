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
import org.springframework.web.bind.annotation.*;

public interface UpdateEntitySupport<Type, Id extends Serializable, RequestType, ResponseType>
		extends ValidationErrorProcessorSupport, EntityServiceSupport<Type, RequestType, Id>, EntityTransformSupport<Type, ResponseType>
{
	static final Logger logger = LogManager.getLogger(UpdateEntitySupport.class);

	@PostMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public default ResponseEntity<Response<ResponseType>> update(final HttpServletRequest request, final HttpServletResponse response, final @PathVariable(name = "id") Id id, final @RequestBody @Valid RequestType dto, final BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) { return processValidationErrors(bindingResult); }

		final EntityService<Type, RequestType, Id> entityService = getEntityService();
		final Type destination = entityService.update(id, dto);

		if (destination == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return ResponseEntity.badRequest().body(Response.badRequest("Unknown identity '" + id + "'"));
		}

		final ResponseType result = this.transform(destination);

		return ResponseEntity.ok().body(Response.ok(result));
	}
}
