package com.locationguru.csf.web.support;

import java.util.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement(name = "criteria")
public class SearchCriteria
{
	private static final Logger logger = LogManager.getLogger(SearchCriteria.class);

	@XmlElement(name = "query")
	private String query = "";

	@XmlElement(name = "filters")
	private Map<String, String> filters = new HashMap<>(0);

	@XmlElement(name = "pagination")
	private PaginationProperties pagination = new PaginationProperties();

	@XmlElement(name = "sorting")
	private List<@Valid SortField> sorting = new ArrayList<>();

	private Map<String, Object> defaultFilters = new HashMap<>(0);

	@XmlTransient
	private int totalResults = 0;

	public boolean isEmpty()
	{
		return query.length() < 3 && filters.isEmpty() && sorting.isEmpty();
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(final String query)
	{
		this.query = query.trim();
	}

	public Map<String, String> getFilters()
	{
		return filters;
	}

	public void setFilters(final Map<String, String> filters)
	{
		this.filters = filters;
	}

	public PaginationProperties getPagination()
	{
		return pagination;
	}

	public void setPagination(final PaginationProperties pagination)
	{
		this.pagination = pagination;
	}

	public List<SortField> getSorting()
	{
		return sorting;
	}

	public void setSorting(final List<SortField> sorting)
	{
		this.sorting = sorting;
	}

	public Map<String, Object> getDefaultFilters()
	{
		return defaultFilters;
	}

	public void setDefaultFilters(final Map<String, Object> defaultFilters)
	{
		this.defaultFilters = defaultFilters;
	}

	public int getTotalResults()
	{
		return totalResults;
	}

	public void setTotalResults(final int totalResults)
	{
		this.totalResults = totalResults;
	}
}
