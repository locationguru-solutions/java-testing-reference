package com.locationguru.csf.web.mvc;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides common implementation for providing entity access endpoints.
 * <p>
 *
 * @param <Type>         type of entity served by the controller.
 * @param <Id>           type of entity identity.
 * @param <RequestType>  type of input consumed  by the controller.
 * @param <ResponseType> type of out produced  by the controller.
 */
public abstract class AbstractController<Type, Id extends Serializable, RequestType, ResponseType>
		implements ListEntitySupport<Type, Id, RequestType, ResponseType>, GetEntitySupport<Type, Id, RequestType, ResponseType>,
						   SaveEntitySupport<Type, Id, RequestType, ResponseType>, UpdateEntitySupport<Type, Id, RequestType, ResponseType>,
						   DeleteEntitySupport<Type, Id, RequestType, ResponseType>
{
	private static final Logger logger = LogManager.getLogger(AbstractController.class);

	protected final EntityService<Type, RequestType, Id> entityService;

	protected AbstractController(final EntityService<Type, RequestType, Id> entityService)
	{
		this.entityService = entityService;
	}

	@Override
	public EntityService<Type, RequestType, Id> getEntityService()
	{
		return entityService;
	}
}
