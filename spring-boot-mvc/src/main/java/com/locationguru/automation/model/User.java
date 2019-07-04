package com.locationguru.automation.model;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.*;

@Entity
@Table(name = "users")
public class User
{
	@Id
	@XmlTransient
	@Column(name = "id")
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	private Long id;

	@Generated(GenerationTime.INSERT)
	@Column(name = "uid", updatable = false, insertable = false)
	private UUID uid;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "identity")
	private String identity;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@XmlTransient
	@Column(name = "is_active")
	private Boolean isActive;

	@XmlTransient
	@CreationTimestamp
	@Column(name = "creation_timestamp")
	private Timestamp creationTimestamp;

	@XmlTransient
	@UpdateTimestamp
	@Column(name = "update_timestamp")
	private Timestamp updateTimestamp;

	@XmlTransient
	@Column(name = "deletion_timestamp")
	private Timestamp deletionTimestamp;

	@Version
	@XmlTransient
	@Column(name = "version")
	private Integer version;

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public UUID getUid()
	{
		return uid;
	}

	public void setUid(final UUID uid)
	{
		this.uid = uid;
	}

	public Long getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(final Long customerId)
	{
		this.customerId = customerId;
	}

	public String getIdentity()
	{
		return identity;
	}

	public void setIdentity(final String identity)
	{
		this.identity = identity;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public Boolean getIsActive()
	{
		return isActive;
	}

	public void setIsActive(final Boolean isActive)
	{
		this.isActive = isActive;
	}

	public Timestamp getCreationTimestamp()
	{
		return creationTimestamp;
	}

	public void setCreationTimestamp(final Timestamp creationTimestamp)
	{
		this.creationTimestamp = creationTimestamp;
	}

	public Timestamp getUpdateTimestamp()
	{
		return updateTimestamp;
	}

	public void setUpdateTimestamp(final Timestamp updateTimestamp)
	{
		this.updateTimestamp = updateTimestamp;
	}

	public Timestamp getDeletionTimestamp()
	{
		return deletionTimestamp;
	}

	public void setDeletionTimestamp(final Timestamp deletionTimestamp)
	{
		this.deletionTimestamp = deletionTimestamp;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(final Integer version)
	{
		this.version = version;
	}
}
