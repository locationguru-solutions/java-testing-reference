package com.locationguru.automation.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.locationguru.automation.model.User;
import com.locationguru.automation.repository.UserRepository;
import com.locationguru.csf.web.mvc.EntityService;
import com.locationguru.csf.web.support.SearchCriteria;
import com.locationguru.csf.web.support.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService
		implements EntityService<User, User, UUID>
{
	private static final Logger logger = LogManager.getLogger(UserService.class);

	private final UserRepository repository;

	@Autowired
	public UserService(final UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll()
	{
		final Long customerId = 1L;
		return repository.findAll(customerId);
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(final UUID uid)
	{
		final Long customerId = 1L;
		return repository.findByUid(customerId, uid);
	}

	@Transactional(readOnly = true)
	public User findByUid(final Long customerId, final UUID uid)
	{
		return repository.findByUid(customerId, uid);
	}

	@Transactional(readOnly = true)
	public User findById(final Long id)
	{
		final Long customerId = 1L;
		return repository.findById(customerId, id);
	}

	@Transactional(readOnly = true)
	public User findById(final Long customerId, final Long id)
	{
		return repository.findById(customerId, id);
	}

	@Override
	@Transactional
	public void save(final User user)
	{
		repository.save(user);
	}

	@Override
	@Transactional
	public void delete(final UUID uid)
	{
		final User user = findById(uid);

		if (user == null)
		{
			throw new ValidationException("Unknown user identity '" + uid + "'");
		}

		user.setIsActive(Boolean.FALSE);
		user.setDeletionTimestamp(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public List<User> search(final SearchCriteria criteria)
	{
		final Long customerId = 1L;
		return repository.findAll(customerId);
	}

	@Override
	public void copy(final User source, final User destination)
	{
		destination.setIdentity(source.getIdentity());
		destination.setFirstName(source.getFirstName());
		destination.setLastName(source.getLastName());
	}

	@Override
	@Transactional
	public User createEntity(final User source)
	{
		final Long customerId = 1L;
		final User destination = new User();

		destination.setCustomerId(customerId);

		copy(source, destination);

		destination.setIsActive(Boolean.TRUE);

		repository.create(destination);

		return destination;
	}
}
