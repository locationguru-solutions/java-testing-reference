package com.locationguru.csf.web.support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaginationProperties
{
	private static final Logger logger = LogManager.getLogger(PaginationProperties.class);

	private int firstResult = 0;

	private int pageSize = 0;

	public int getFirstResult()
	{
		return firstResult;
	}

	public void setFirstResult(final int firstResult)
	{
		this.firstResult = firstResult;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(final int pageSize)
	{
		this.pageSize = pageSize;
	}
}
