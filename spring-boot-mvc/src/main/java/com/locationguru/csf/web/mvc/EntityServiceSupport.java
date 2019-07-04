package com.locationguru.csf.web.mvc;

public interface EntityServiceSupport<Type, RequestType, Id>
{
	public EntityService<Type, RequestType, Id> getEntityService();
}
