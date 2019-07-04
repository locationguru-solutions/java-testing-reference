package com.locationguru.csf.web.mvc;

import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locationguru.csf.web.rest.model.PaginatedResponse;
import com.locationguru.csf.web.rest.model.Response;
import com.locationguru.csf.web.support.PaginationProperties;
import com.locationguru.csf.web.support.SearchCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ListEntitySupport<Type, Id extends Serializable, RequestType, ResponseType>
		extends EntityServiceSupport<Type, RequestType, Id>, EntityTransformSupport<Type, ResponseType>
{
	static final Logger logger = LogManager.getLogger(ListEntitySupport.class);

	@GetMapping(path = { "/", "" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public default ResponseEntity<Response<ResponseType>> list(final HttpServletRequest request, final HttpServletResponse response)
	{
		final List<Type> list = getEntityService().findAll();
		final List<ResponseType> result = this.transform(list);

		return ResponseEntity.ok().body(Response.ok(result));
	}

	@PostMapping(path = { "/search" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public default ResponseEntity<PaginatedResponse<ResponseType>> search(final HttpServletRequest request, final HttpServletResponse response, final @RequestBody SearchCriteria criteria)
	{
		final List<Type> list = getEntityService().search(criteria);
		final PaginatedResponse<ResponseType> paginatedResponse = PaginatedResponse.ok(this.transform(list));

		final PaginationProperties pagination = criteria.getPagination();

		paginatedResponse.setPageStart(pagination.getFirstResult());
		paginatedResponse.setPageSize(pagination.getPageSize());
		paginatedResponse.setTotalRecords(criteria.getTotalResults());

		return ResponseEntity.ok().body(paginatedResponse);
	}
}
