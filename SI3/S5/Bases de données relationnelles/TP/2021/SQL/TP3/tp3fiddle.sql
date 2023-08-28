CREATE TABLE regions (
    region_id SERIAL PRIMARY KEY,
    region_name character varying(25)
);


CREATE TABLE countries (
    country_id character(2) PRIMARY KEY,
    country_name character varying(40),
    region_id integer NOT NULL REFERENCES regions
);



CREATE TABLE locations (
    location_id SERIAL PRIMARY KEY,
    street_address character varying(40),
    postal_code character varying(12),
    city character varying(30) NOT NULL,
    state_province character varying(25),
    country_id character(2) NOT NULL REFERENCES countries
);

CREATE TABLE departments (
    department_id SERIAL PRIMARY KEY,
    department_name character varying(30) NOT NULL,
    location_id integer REFERENCES locations
);


CREATE TABLE jobs (
    job_id SERIAL PRIMARY KEY,
    job_title character varying(35) NOT NULL,
    min_salary numeric(8,2),
    max_salary numeric(8,2)
);

CREATE TABLE employees (
    employee_id SERIAL PRIMARY KEY,
    first_name character varying(20),
    last_name character varying(25) NOT NULL,
    email character varying(100) NOT NULL,
    phone_number character varying(20),
    hire_date date NOT NULL,
    job_id integer NOT NULL REFERENCES jobs,
    salary numeric(8,2) NOT NULL,
    manager_id integer REFERENCES employees,
    department_id integer REFERENCES departments
);


CREATE TABLE salary_changes (
    employee_id integer NOT NULL,
    changed_at timestamp(4) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    old_salary numeric(8,2),
    new_salary numeric(8,2),
    modifiant text
);



INSERT INTO public.regions VALUES (1, 'Europe'), (2, 'Americas');

INSERT INTO public.countries VALUES ('AR', 'Argentina', 2),('CA', 'Canada', 2),
('DE', 'Germany', 1),('UK', 'United Kingdom', 1),('US', 'United States of America', 2);


INSERT INTO public.locations VALUES (1500, '2011 Interiors Blvd', '99236', 'South San Francisco', 'California', 'US'), (1700, '2004 Charade Rd', '98199', 'Seattle', 'Washington', 'US'), (1800, '147 Spadina Ave', 'M5V 2L7', 'Toronto', 'Ontario', 'CA'), (2400, '8204 Arthur St', NULL, 'London', NULL, 'UK'), (2500, 'Magdalen Centre, The Oxford Science Park', 'OX9 9ZB', 'Oxford', 'Oxford', 'UK'),(2700, 'Schwanthalerstr. 7031', '80925', 'Munich', 'Bavaria', 'DE');

INSERT INTO public.departments VALUES (1, 'Administration', 1700), (2, 'Marketing', 1800), (3, 'Purchasing', 1700), (4, 'Human Resources', 2400), (5, 'Shipping', 1500), (7, 'Public Relations', 2700), (8, 'Sales', 2500), (9, 'Executive', 1700), (10, 'Finance', 1700), (11, 'Accounting', 1700);

INSERT INTO public.jobs VALUES (1, 'Public Accountant', 4200.00, 9000.00),(2, 'Accounting Manager', 8200.00, 16000.00), 
(3, 'Administration Assistant', 3000.00, 6000.00), (4, 'President', 20000.00, 40000.00),(5, 'Administration Vice President', 15000.00, 30000.00),
(6, 'Accountant', 4200.00, 9000.00),(7, 'Finance Manager', 8200.00, 16000.00),
(8, 'Human Resources Representative', 4000.00, 9000.00), (9, 'Programmer', 4000.00, 10000.00),(10, 'Marketing Manager', 9000.00, 15000.00),
(11, 'Marketing Representative', 4000.00, 9000.00),(12, 'Public Relations Representative', 4500.00, 10500.00),(13, 'Purchasing Clerk', 2500.00, 5500.00),(14, 'Purchasing Manager', 8000.00, 15000.00),
 (15, 'Sales Manager', 10000.00, 20000.00),(16, 'Sales Representative', 6000.00, 12000.00), (17, 'Shipping Clerk', 2500.00, 5500.00), (18, 'Stock Clerk', 2000.00, 5000.00),(19, 'Stock Manager', 5500.00, 8500.00);

INSERT INTO public.employees VALUES (101, 'Neena', 'Kochhar', 'neena.kochhar@sqltutorial.org', '515.123.4568', '1989-09-21', 5, 17000.00, 100, 9),(102, 'Lex', 'De Haan', 'lex.de haan@sqltutorial.org', '515.123.4569', '1993-01-13', 5, 17000.00, 100, 9), (108, 'Nancy', 'Greenberg', 'nancy.greenberg@sqltutorial.org', '515.124.4569', '1994-08-17', 7, 12000.00, 101, 10),(109, 'Daniel', 'Faviet', 'daniel.faviet@sqltutorial.org', '515.124.4169', '1994-08-16', 6, 9000.00, 108, 10), (110, 'John', 'Chen', 'john.chen@sqltutorial.org', '515.124.4269', '1997-09-28', 6, 8200.00, 108, 10),
(111, 'Ismael', 'Sciarra', 'ismael.sciarra@sqltutorial.org', '515.124.4369', '1997-09-30', 6, 7700.00, 108, 10),(112, 'Jose Manuel', 'Urman', 'jose manuel.urman@sqltutorial.org', '515.124.4469', '1998-03-07', 6, 7800.00, 108, 10),(113, 'Luis', 'Popp', 'luis.popp@sqltutorial.org', '515.124.4567', '1999-12-07', 6, 6900.00, 108, 10),(114, 'Den', 'Raphaely', 'den.raphaely@sqltutorial.org', '515.127.4561', '1994-12-07', 14, 11000.00, 100, 3),(115, 'Alexander', 'Khoo', 'alexander.khoo@sqltutorial.org', '515.127.4562', '1995-05-18', 13, 3100.00, 114, 3),(116, 'Shelli', 'Baida', 'shelli.baida@sqltutorial.org', '515.127.4563', '1997-12-24', 13, 2900.00, 114, 3),(117, 'Sigal', 'Tobias', 'sigal.tobias@sqltutorial.org', '515.127.4564', '1997-07-24', 13, 2800.00, 114, 3),(118, 'Guy', 'Himuro', 'guy.himuro@sqltutorial.org', '515.127.4565', '1998-11-15', 13, 2600.00, 114, 3),(119, 'Karen', 'Colmenares', 'karen.colmenares@sqltutorial.org', '515.127.4566', '1999-08-10', 13, 2500.00, 114, 3),(120, 'Matthew', 'Weiss', 'matthew.weiss@sqltutorial.org', '650.123.1234', '1996-07-18', 19, 8000.00, 100, 5),(121, 'Adam', 'Fripp', 'adam.fripp@sqltutorial.org', '650.123.2234', '1997-04-10', 19, 8200.00, 100, 5),(122, 'Payam', 'Kaufling', 'payam.kaufling@sqltutorial.org', '650.123.3234', '1995-05-01', 19, 7900.00, 100, 5),(123, 'Shanta', 'Vollman', 'shanta.vollman@sqltutorial.org', '650.123.4234', '1997-10-10', 19, 6500.00, 100, 5),(126, 'Irene', 'Mikkilineni', 'irene.mikkilineni@sqltutorial.org', '650.124.1224', '1998-09-28', 18, 2700.00, 120, 5),(145, 'John', 'Russell', 'john.russell@sqltutorial.org', NULL, '1996-10-01', 15, 14000.00, 100, 8),
(146, 'Karen', 'Partners', 'karen.partners@sqltutorial.org', NULL, '1997-01-05', 15, 13500.00, 100, 8),(176, 'Jonathon', 'Taylor', 'jonathon.taylor@sqltutorial.org', NULL, '1998-03-24', 16, 8600.00, 100, 8),(177, 'Jack', 'Livingston', 'jack.livingston@sqltutorial.org', NULL, '1998-04-23', 16, 8400.00, 100, 8), (178, 'Kimberely', 'Grant', 'kimberely.grant@sqltutorial.org', NULL, '1999-05-24', 16, 7000.00, 100, 8),(179, 'Charles', 'Johnson', 'charles.johnson@sqltutorial.org', NULL, '2000-01-04', 16, 6200.00, 100, 8),
(192, 'Sarah', 'Bell', 'sarah.bell@sqltutorial.org', '650.501.1876', '1996-02-04', 17, 4000.00, 123, 5), (193, 'Britney', 'Everett', 'britney.everett@sqltutorial.org', '650.501.2876', '1997-03-03', 17, 3900.00, 123, 5),(200, 'Jennifer', 'Whalen', 'jennifer.whalen@sqltutorial.org', '515.123.4444', '1987-09-17', 3, 4400.00, 101, 1),(201, 'Michael', 'Hartstein', 'michael.hartstein@sqltutorial.org', '515.123.5555', '1996-02-17', 10, 13000.00, 100, 2), (202, 'Pat', 'Fay', 'pat.fay@sqltutorial.org', '603.123.6666', '1997-08-17', 11, 6000.00, 201, 2), (203, 'Susan', 'Mavris', 'susan.mavris@sqltutorial.org', '515.123.7777', '1994-06-07', 8, 6500.00, 101, 4),(204, 'Hermann', 'Baer', 'hermann.baer@sqltutorial.org', '515.123.8888', '1994-06-07', 12, 10000.00, 101, 7),(205, 'Shelley', 'Higgins', 'shelley.higgins@sqltutorial.org', '515.123.8080', '1994-06-07', 2, 12000.00, 101, 11),(206, 'William', 'Gietz', 'william.gietz@sqltutorial.org', '515.123.8181', '1994-06-07', 1, 8300.00, 205, 11), (100, 'Steven', 'King', 'steven.king@sqltutorial.org', '515.123.4567', '1987-06-17', 4, 30100.00, NULL, 9);

