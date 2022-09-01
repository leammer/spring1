-- PostgreSQL database

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA IF NOT EXISTS test;

SET search_path = test;

SET default_tablespace = '';

SET default_with_oids = false;

\c test

--
-- Name: product; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS product (
	id bigserial NOT NULL,
	price numeric(10, 2) NOT NULL,
	title varchar(30) NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);

--
-- Name: customer; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS customer (
	id bigserial NOT NULL,
	name varchar(30) NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

--
-- Name: item; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS item (
	id bigserial NOT NULL,
	quantity int8,
	customer_id int8 NOT NULL,
	product_id int8 NOT NULL,
	CONSTRAINT item_pkey PRIMARY KEY (id),
	CONSTRAINT customer_fkey FOREIGN KEY (customer_id) REFERENCES customer(id),
	CONSTRAINT product_fkey FOREIGN KEY (product_id) REFERENCES product(id)
);

--
-- Data for Name: product; Type: TABLE DATA; Schema: test;
--

INSERT INTO product VALUES (DEFAULT, 10.15, 'Milk');
INSERT INTO product VALUES (DEFAULT, 22.00, 'Bread');
INSERT INTO product VALUES (DEFAULT, 32.50, 'Carrot');
INSERT INTO product VALUES (DEFAULT, 400, 'Onion');
INSERT INTO product VALUES (DEFAULT, 429.53, 'Chicken');

--
-- Data for Name: customer; Type: TABLE DATA; Schema: test;
--

INSERT INTO customer VALUES (DEFAULT, 'Ivan Ivanov');
INSERT INTO customer VALUES (DEFAULT, 'Peter Petrov');
INSERT INTO customer VALUES (DEFAULT, 'Semyon Semenov');

--
-- Data for Name: item; Type: TABLE DATA; Schema: test;
--

-- Ivan Ivanov's package: 2 milks, 1 bread
INSERT INTO item VALUES (DEFAULT, 2, 1, 1);
INSERT INTO item VALUES (DEFAULT, 1, 1, 2);

-- Peter Petrov's package: 1 chicken, 3 carrots, 2 onions
INSERT INTO item VALUES (DEFAULT, 1, 2, 5);
INSERT INTO item VALUES (DEFAULT, 3, 2, 3);
INSERT INTO item VALUES (DEFAULT, 2, 2, 4);

-- Semyon Semenov's package: 8 breads
INSERT INTO item VALUES (DEFAULT, 8, 3, 2);

--
-- PostgreSQL database complete
--


