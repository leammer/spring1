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
	price numeric(10, 2) NULL,
	deleted bool NOT NULL,
	description varchar(255) NULL,
	title varchar(30) NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);

--
-- Name: users; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS users (
	id bigserial NOT NULL,
	"password" varchar(1024) NOT NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT fk_username UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

--
-- Name: roles; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS roles (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT fk_name UNIQUE (name),
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);

--
-- Name: users_roles; Type: TABLE; Schema: test;
--
CREATE TABLE IF NOT EXISTS users_roles (
	fk_user int8 NOT NULL,
	fk_role int8 NOT NULL,
	CONSTRAINT fk_role_user FOREIGN KEY (fk_role) REFERENCES roles(id),
	CONSTRAINT fk_user_role FOREIGN KEY (fk_user) REFERENCES users(id)
);

--
-- Name: customer; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS customer (
	id bigserial NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	issued_by varchar(255) NULL,
	issued_date timestamp NULL,
	"number" varchar(255) NULL,
	serial varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id),
	CONSTRAINT fk_customer_user FOREIGN KEY (user_id) REFERENCES users(id)
);

--
-- Name: contact; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS contact (
	id bigserial NOT NULL,
	"type" varchar(255) NOT NULL,
	value varchar(255) NOT NULL,
	customer_id int8 NULL,
	CONSTRAINT contact_pkey PRIMARY KEY (id),
	CONSTRAINT fk_contact_customer FOREIGN KEY (customer_id) REFERENCES public.customer(id)
);

--
-- Name: cart; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS cart (
	id bigserial NOT NULL,
	customer_id int8 NULL,
	CONSTRAINT cart_pkey PRIMARY KEY (id),
	CONSTRAINT fk_cart_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

--
-- Name: item; Type: TABLE; Schema: test;
--

CREATE TABLE IF NOT EXISTS item (
	id bigserial NOT NULL,
	quantity int8,
	cart_id int8 NOT NULL,
	product_id int8 NOT NULL,
	CONSTRAINT item_pkey PRIMARY KEY (id),
	CONSTRAINT item_cart_fkey FOREIGN KEY (cart_id) REFERENCES cart(id),
	CONSTRAINT item_product_fkey FOREIGN KEY (product_id) REFERENCES product(id)
);

--
-- Data for Name: product; Type: TABLE DATA; Schema: test;
--

INSERT INTO product VALUES (DEFAULT,  10.15, false, '', 'Milk');
INSERT INTO product VALUES (DEFAULT,  22.00, false, '','Bread');
INSERT INTO product VALUES (DEFAULT,  32.50, false, '','Carrot');
INSERT INTO product VALUES (DEFAULT, 400.00, false, '','Onion');
INSERT INTO product VALUES (DEFAULT, 429.53, false, '','Chicken');
INSERT INTO product VALUES (DEFAULT, 326.55, false, '','Beef');
INSERT INTO product VALUES (DEFAULT, 435.33, false, '','Pork');
INSERT INTO product VALUES (DEFAULT, 543.25, false, '','Cod');
INSERT INTO product VALUES (DEFAULT, 453.57, false, '','Salmon');
INSERT INTO product VALUES (DEFAULT,  14.31, false, '','Cabbage');
INSERT INTO product VALUES (DEFAULT,   8.90, false, '','Cucumber');
INSERT INTO product VALUES (DEFAULT,  40.53, false, '','Garlic');
INSERT INTO product VALUES (DEFAULT,  10.75, false, '','Potato');
INSERT INTO product VALUES (DEFAULT,  83.00, false, '','Apple');
INSERT INTO product VALUES (DEFAULT, 145.35, false, '','Banana');
INSERT INTO product VALUES (DEFAULT,2390.55, false, '','Cherry');
INSERT INTO product VALUES (DEFAULT, 121.37, false, '','Lemon');
INSERT INTO product VALUES (DEFAULT,  45.42, false, '','Rice');
INSERT INTO product VALUES (DEFAULT, 180.54, false, '','Butter');
INSERT INTO product VALUES (DEFAULT, 250.65, false, '','Cheese');

--
-- Data for Name: users; Type: TABLE DATA; Schema: test;
--
INSERT INTO users VALUES (DEFAULT,  '$2a$10$GMeyncHgI1onEoeglEJdZ.VabMnV9NeoeEt0hBywRLqMrtLVc7IFe', 'user1');
INSERT INTO users VALUES (DEFAULT,  '$2a$10$GMeyncHgI1onEoeglEJdZ.VabMnV9NeoeEt0hBywRLqMrtLVc7IFe', 'user2');
INSERT INTO users VALUES (DEFAULT,  '$2a$10$GMeyncHgI1onEoeglEJdZ.VabMnV9NeoeEt0hBywRLqMrtLVc7IFe', 'user3');
INSERT INTO users VALUES (DEFAULT,  '$2a$10$GMeyncHgI1onEoeglEJdZ.VabMnV9NeoeEt0hBywRLqMrtLVc7IFe', 'user4');

--
-- Data for Name: roles; Type: TABLE DATA; Schema: test;
--

INSERT INTO roles VALUES (DEFAULT,  'ROLE_USER');
INSERT INTO roles VALUES (DEFAULT,  'ROLE_MANAGER');
INSERT INTO roles VALUES (DEFAULT,  'ROLE_ADMIN');
INSERT INTO roles VALUES (DEFAULT,  'ROLE_SUPERUSER');

--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: test;
--
-- user1 has role user --
INSERT INTO users_roles VALUES (1,  1);
-- user2 has roles user, manager --
INSERT INTO users_roles VALUES (2,  1);
INSERT INTO users_roles VALUES (2,  2);
-- user3 has roles user, manager, admin --
INSERT INTO users_roles VALUES (3,  1);
INSERT INTO users_roles VALUES (3,  2);
INSERT INTO users_roles VALUES (3,  3);
-- user4 has roles user, manager, admin, superadmin --
INSERT INTO users_roles VALUES (4,  1);
INSERT INTO users_roles VALUES (4,  2);
INSERT INTO users_roles VALUES (4,  3);
INSERT INTO users_roles VALUES (4,  4);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: test;
--
INSERT INTO customer VALUES (DEFAULT, 'Ivan', 'Ivanov', NULL, NULL, NULL, NULL, 1);
INSERT INTO customer VALUES (DEFAULT, 'Peter', 'Petrov', NULL, NULL, NULL, NULL, 2);
INSERT INTO customer VALUES (DEFAULT, 'Semyon', 'Semenov', NULL, NULL, NULL, NULL, 3);
INSERT INTO customer VALUES (DEFAULT, 'Danil', 'Danilov', NULL, NULL, NULL, NULL, 4);

--
-- Data for Name: contact; Type: TABLE DATA; Schema: test;
--
-- Ivan Ivanov's contacts
INSERT INTO contact VALUES (DEFAULT, 'MOBILE_PHONE', '+71234567890', 1);
INSERT INTO contact VALUES (DEFAULT, 'ADDRESS', 'Some address', 1);
-- Semyon Semenov's contacts
INSERT INTO contact VALUES (DEFAULT, 'EMAIL', 'semenov@mail.ru', 3);

--
-- Data for Name: cart; Type: TABLE DATA; Schema: test;
--
INSERT INTO cart VALUES (DEFAULT, 1);
INSERT INTO cart VALUES (DEFAULT, 2);
INSERT INTO cart VALUES (DEFAULT, 3);
INSERT INTO cart VALUES (DEFAULT, 4);

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


