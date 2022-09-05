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

--
-- Data for Name: product; Type: TABLE DATA; Schema: test;
--

INSERT INTO product VALUES (DEFAULT, 10.15, 'Milk');
INSERT INTO product VALUES (DEFAULT, 22.00, 'Bread');
INSERT INTO product VALUES (DEFAULT, 32.50, 'Carrot');
INSERT INTO product VALUES (DEFAULT, 400, 'Onion');
INSERT INTO product VALUES (DEFAULT, 429.53, 'Chicken');
INSERT INTO product VALUES (DEFAULT, 326.55, 'Beef');
INSERT INTO product VALUES (DEFAULT, 435.33, 'Pork');
INSERT INTO product VALUES (DEFAULT, 543.25, 'Cod');
INSERT INTO product VALUES (DEFAULT, 453.57, 'Salmon');
INSERT INTO product VALUES (DEFAULT, 14.31, 'Cabbage');
INSERT INTO product VALUES (DEFAULT, 8.90, 'Cucumber');
INSERT INTO product VALUES (DEFAULT, 40.53, 'Garlic');
INSERT INTO product VALUES (DEFAULT, 10.75, 'Potato');
INSERT INTO product VALUES (DEFAULT, 83.00, 'Apple');
INSERT INTO product VALUES (DEFAULT, 145.35, 'Banana');
INSERT INTO product VALUES (DEFAULT, 2390.55, 'Cherry');
INSERT INTO product VALUES (DEFAULT, 121.37, 'Lemon');
INSERT INTO product VALUES (DEFAULT, 45.42, 'Rice');
INSERT INTO product VALUES (DEFAULT, 180.54, 'Butter');
INSERT INTO product VALUES (DEFAULT, 250.65, 'Cheese');

--
-- PostgreSQL database complete
--


