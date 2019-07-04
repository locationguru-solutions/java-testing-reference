package com.locationguru.automation.repository;

import java.util.List;
import java.util.UUID;

import com.locationguru.automation.model.User;
import com.locationguru.csf.repository.BaseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository
		extends BaseRepository<User>
{
	private static final Logger logger = LogManager.getLogger(UserRepository.class);

	public List<User> findAll()
	{
		return db.createQuery("SELECT u FROM User u WHERE u.isActive = TRUE", User.class)
				 .getResultList();
	}

	public List<User> findAll(final Long customerId)
	{
		return db.createQuery("SELECT u FROM User u WHERE u.customerId = :customerId AND u.isActive = TRUE", User.class)
				 .setParameter("customerId", customerId)
				 .getResultList();
	}

	public User findById(final Long id)
	{
		return first(db.createQuery("SELECT u FROM User u WHERE u.id = :id AND u.isActive = TRUE", User.class)
					   .setParameter("id", id)
					   .getResultList());
	}

	public User findById(final Long customerId, final Long id)
	{
		return first(db.createQuery("SELECT u FROM User u WHERE u.id = :id AND u.customerId = :customerId AND u.isActive = TRUE", User.class)
					   .setParameter("id", id)
					   .setParameter("customerId", customerId)
					   .getResultList());
	}

	public User findByUid(final UUID uid)
	{
		return first(db.createQuery("SELECT u FROM User u WHERE u.uid = :uid AND u.isActive = TRUE", User.class)
					   .setParameter("uid", uid)
					   .getResultList());
	}

	public User findByUid(final Long customerId, final UUID uid)
	{
		return first(db.createQuery("SELECT u FROM User u WHERE u.uid = :uid AND u.customerId = :customerId AND u.isActive = TRUE", User.class)
					   .setParameter("uid", uid)
					   .setParameter("customerId", customerId)
					   .getResultList());
	}

	public User findByIdentity(final String identity)
	{
		return first(db.createQuery("SELECT u FROM User u WHERE lower(u.identity) = lower(:identity) AND u.isActive = TRUE", User.class)
					   .setParameter("identity", identity)
					   .getResultList());
	}

	public User findByIdentity(final Long customerId, final String identity)
	{
		return first(db.createQuery("SELECT u FROM User u WHERE lower(u.identity) = lower(:identity) AND u.customerId = :customerId AND u.isActive = TRUE", User.class)
					   .setParameter("identity", identity)
					   .setParameter("customerId", customerId)
					   .getResultList());
	}
}
