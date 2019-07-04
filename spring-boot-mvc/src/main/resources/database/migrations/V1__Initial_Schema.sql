-- JUnit Reference

-- Initialization

-- DROP DATABASE IF EXISTS "junit-reference";
-- CREATE DATABASE "junit-reference" WITH ENCODING = 'UTF8';

-- Extensions

-- UUID
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA public VERSION '1.1';

-- users

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
	id                 BIGSERIAL NOT NULL PRIMARY KEY,

	uid                UUID      NOT NULL,
	customer_id        BIGINT    NOT NULL,

	identity           TEXT      NOT NULL,

	first_name         TEXT      NOT NULL,
	last_name          TEXT      NOT NULL,

	is_active          BOOLEAN   NOT NULL DEFAULT TRUE,

	creation_timestamp TIMESTAMP NOT NULL DEFAULT current_timestamp,
	update_timestamp   TIMESTAMP NOT NULL DEFAULT current_timestamp,
	deletion_timestamp TIMESTAMP NULL,

	version            INTEGER   NOT NULL DEFAULT 0
);