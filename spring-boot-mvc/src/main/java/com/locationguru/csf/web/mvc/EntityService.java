package com.locationguru.csf.web.mvc;

import java.util.List;

import com.locationguru.csf.web.support.SearchCriteria;

public interface EntityService<Type, DTO, Id>
{
	public List<Type> findAll();

	public Type findById(final Id id);

	public void save(final Type type);

	public void delete(final Id id);

	public default List<Type> search(final SearchCriteria criteria)
	{
		return findAll();
	}

	public void copy(final DTO source, final Type destination);

	public Type createEntity(final DTO source);

	// @Transactional
	public default Type update(final Id id, final DTO dto)
	{
		final Type destination = this.findById(id);

		if (destination != null)
		{
			this.copy(dto, destination);
			this.save(destination);
		}

		return destination;
	}
}
