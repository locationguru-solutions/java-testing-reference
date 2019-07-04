package com.locationguru.csf.web.mvc;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locationguru.csf.web.rest.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface DeleteEntitySupport<Type, Id extends Serializable, RequestType, ResponseType>
		extends EntityServiceSupport<Type, RequestType, Id>
{
	static final Logger logger = LogManager.getLogger(DeleteEntitySupport.class);

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public default ResponseEntity<Response<ResponseType>> delete(final HttpServletRequest request, final HttpServletResponse response, final @PathVariable(name = "id") Id id)
	{
		getEntityService().delete(id);
		return ResponseEntity.ok().body(Response.ok());
	}

	@PostMapping(path = "/{id}/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
	public default ResponseEntity<Response<ResponseType>> delete2(final HttpServletRequest request, final HttpServletResponse response, final @PathVariable(name = "id") Id id)
	{
		return delete(request, response, id);
	}
}
