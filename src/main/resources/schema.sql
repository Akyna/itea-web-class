DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
			login VARCHAR(50) NOT NULL UNIQUE,
			full_name VARCHAR(255) NOT NULL,
			password VARCHAR(64) NOT NULL,
			region VARCHAR(32) NOT NULL,
			gender CHARACTER(1) NOT NULL,
			comment VARCHAR(255) NOT NULL,
			role VARCHAR(64) NOT NULL DEFAULT 'USER');

DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
			name VARCHAR(64) NOT NULL UNIQUE);

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
			name VARCHAR(64) NOT NULL UNIQUE,
			description VARCHAR(255) NOT NULL,
			price float4 NOT NULL,
			category_id BIGINT NOT NULL,
		CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES category(id));

