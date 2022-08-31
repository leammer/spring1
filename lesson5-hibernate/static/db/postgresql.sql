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
    id bigserial,
    price numeric(10, 2),
    title character varying(30) NOT NULL
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
-- Name: course_pkey; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- PostgreSQL database complete
--


