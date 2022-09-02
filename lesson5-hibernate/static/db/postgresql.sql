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
    id serial,
    title character varying(30) NOT NULL,
    price numeric(10, 2)
);


--
-- Data for Name: product; Type: TABLE DATA; Schema: test;
--

INSERT INTO product VALUES (DEFAULT, 'Milk', 10.15);
INSERT INTO product VALUES (DEFAULT, 'Bread', 22.00);
INSERT INTO product VALUES (DEFAULT, 'Carrot', 32.50);
INSERT INTO product VALUES (DEFAULT, 'Onion', 400.00);
INSERT INTO product VALUES (DEFAULT, 'Chicken', 429.53);


--
-- Name: course_pkey; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- PostgreSQL database complete
--


