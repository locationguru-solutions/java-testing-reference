package com.locationguru.csf.web.support;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement(name = "sort")
public class SortField
{
	private static final Logger logger = LogManager.getLogger(SortField.class);

	@NotNull
	@NotEmpty
	private String field;

	private boolean ascending = true;

	public SortField()
	{
	}

	public SortField(final String field, final boolean ascending)
	{
		this.field = field;
		this.ascending = ascending;
	}

	public String getField()
	{
		return field;
	}

	public void setField(final String field)
	{
		this.field = field;
	}

	public boolean isAscending()
	{
		return ascending;
	}

	public void setAscending(final boolean ascending)
	{
		this.ascending = ascending;
	}
}
