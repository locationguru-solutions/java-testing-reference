package com.locationguru.csf.web.rest.model;

import java.util.List;
import javax.xml.bind.annotation.*;

import org.springframework.http.HttpStatus;

@XmlRootElement(name = "response")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PaginatedResponse<T>
		extends Response<T>
{
	@XmlElement(name = "iDisplayStartVal")
	private int pageStart;

	@XmlElement(name = "iDisplayLengthVal")
	private int pageSize;

	@XmlElement(name = "iTotalRecords")
	private int totalRecords;

	public PaginatedResponse(final HttpStatus status)
	{
		super(status);
	}

	public PaginatedResponse(final HttpStatus status, final String message)
	{
		super(status, message);
	}

	public PaginatedResponse(final HttpStatus httpStatus, final T result)
	{
		super(httpStatus, result);
	}

	public PaginatedResponse(final HttpStatus httpStatus, final List<T> results)
	{
		super(httpStatus, results);
	}

	public static <T> PaginatedResponse<T> ok(final T result)
	{
		return new PaginatedResponse<>(HttpStatus.OK, result);
	}

	public static <T> PaginatedResponse<T> ok(final List<T> results)
	{
		return new PaginatedResponse<>(HttpStatus.OK, results);
	}

	public static <T> PaginatedResponse<T> of(final HttpStatus status)
	{
		return new PaginatedResponse<>(status);
	}

	public static <T> Response<T> of(final HttpStatus status, final String message)
	{
		return new PaginatedResponse<>(status, message);
	}

	@XmlElement(name = "iTotalDisplayRecords")
	public int getTotalDisplayRecords()
	{
		return this.totalRecords;
	}

	public int getPageStart()
	{
		return pageStart;
	}

	public void setPageStart(final int pageStart)
	{
		this.pageStart = pageStart;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(final int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(final int totalRecords)
	{
		this.totalRecords = totalRecords;
	}
}