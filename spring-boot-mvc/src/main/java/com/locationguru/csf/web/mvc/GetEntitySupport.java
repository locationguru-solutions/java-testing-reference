package com.locationguru.csf.web.mvc;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locationguru.csf.web.rest.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface GetEntitySupport<Type, Id extends Serializable, RequestType, ResponseType>
		extends EntityServiceSupport<Type, RequestType, Id>, EntityTransformSupport<Type, ResponseType>

{
	static final Logger logger = LogManager.getLogger(GetEntitySupport.class);

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public default ResponseEntity<Response<ResponseType>> get(final HttpServletRequest request, HttpServletResponse response, final @PathVariable(name = "id") Id id)
	{
		final Type type = getEntityService().findById(id);

		if (type == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return ResponseEntity.badRequest().body(Response.badRequest("Unknown identity '" + id + "'"));
		}

		final ResponseType result = this.transform(type);

		return ResponseEntity.ok().body(Response.ok(result));
	}
}
