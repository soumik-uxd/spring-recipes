SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    age integer NOT NULL,
    email character varying(255),
    employee_id character varying(255) NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);

ALTER TABLE public.employees OWNER TO postgres;

--
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (employee_id);

--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E101', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E102', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E103', 'Mohan', 'Reddy');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'sohan.singh@gmail.com', 'E104', 'Sohan', 'Kumar');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'sachin.tendulkar@gmail.com', 'E105', 'Sachin', 'Tendulkar');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'james@gmail.com', 'E106', 'James', 'Gosling');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'javacodingskills@gmail.com', 'E107', 'Java', 'CodingSkills');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'spring.batch@gmail.com', 'E108', 'Spring', 'Batch');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'springboot@gmail.com', 'E109', 'Spring', 'Boot');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E1010', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E1011', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E1012', 'Mohan', 'Reddy');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E1013', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E1014', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E1015', 'Mohan', 'Reddy');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E1016', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E1017', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E1018', 'Mohan', 'Reddy');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E1019', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E1020', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E1021', 'Mohan', 'Reddy');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E1022', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E1023', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E1024', 'Mohan', 'Reddy');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E1025', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E1026', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E1027', 'Mohan', 'Reddy');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (45, 'ram.singh@gmail.com', 'E1028', 'Ram', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (35, 'shyam.singh@gmail.com', 'E1029', 'Shyam', 'Singh');
INSERT INTO public.employees (age, email, employee_id, first_name, last_name) VALUES (39, 'mohan.reddy@gmail.com', 'E1030', 'Mohan', 'Reddy');


--
-- PostgreSQL database dump complete
--