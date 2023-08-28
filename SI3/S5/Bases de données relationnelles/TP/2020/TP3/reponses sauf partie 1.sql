--Q1
SELECT region_name, 
country_name, 
locations.city, 
COUNT( DISTINCT department_name) AS "Nombre de départements",
COUNT (DISTINCT employee_id) AS "Effectif total"
FROM employees 
JOIN departments USING(department_id)
JOIN locations  USING(location_id) 
JOIN countries USING(country_id)
JOIN regions USING(region_id)
GROUP BY region_name, country_name, locations.city
ORDER BY region_name, country_name, locations.city;


--Q2
SELECT region_name, 
country_name, 
locations.city, 
COUNT( DISTINCT department_name) AS "Nombre de départements",
COUNT (DISTINCT employee_id) AS "Effectif total"
FROM employees 
JOIN departments USING(department_id)
JOIN locations  USING(location_id) 
JOIN countries USING(country_id)
JOIN regions USING(region_id)
GROUP BY
 GROUPING SETS(
        (region_name, country_name, locations.city),
        (region_name, country_name),
        (region_name),
        ()
    )
ORDER BY region_name, country_name, locations.city;

--Q3
SELECT 
COALESCE(region_name, 'Toutes les regions') as "Region", 
COALESCE (country_name, 'Tous les pays') as "Pays",
COALESCE (locations.city, 'Toutes les villes') as "Villes",
COUNT( DISTINCT department_name) AS "Nombre de départements",
COUNT (DISTINCT employee_id) AS "Effectif total"
FROM employees 
JOIN departments USING(department_id)
JOIN locations  USING(location_id) 
JOIN countries USING(country_id)
JOIN regions USING(region_id)
GROUP BY
 GROUPING SETS(
        (region_name, country_name, locations.city),
        (region_name, country_name),
        (region_name),
        ()
    )
ORDER BY region_name, country_name, locations.city;
--Q4
SELECT
    COALESCE(region_name, 'Toutes les regions') as "Region", 
	COALESCE (country_name, 'Tous les pays') as "Pays",
	COALESCE (locations.city, 'Toutes les villes') as "Villes", COUNT (department_id) NbDept,
	string_agg( DISTINCT department_name, ' - ' ORDER BY department_name) as "Nom des départements",
	count (distinct employee_id) AS "Effectif total"
FROM employees 
JOIN departments USING(department_id)
JOIN locations  USING(location_id) 
JOIN countries USING(country_id)
JOIN regions USING(region_id)
GROUP BY
 GROUPING SETS(
        (region_name,country_name, locations.city),
        (region_name,country_name),
        (region_name),
        ()
    )
   
ORDER BY region_name,country_name, locations.city;

--Q5
SELECT
    COALESCE(region_name, 'Toutes les regions: '||string_agg(DISTINCT region_name, ', ' ORDER BY region_name) ) as region,
    COALESCE (country_name, 'Tous les pays: '||string_agg(DISTINCT country_name, ', ' ORDER BY country_name)) as pays,
    COALESCE ( locations.city , 'Toutes les  villes: '||string_agg(DISTINCT locations.city, ', ' ORDER BY locations.city)) as ville,
    COUNT (department_id) NbDept,
	string_agg( DISTINCT department_name, ' - ' ORDER BY department_name) as "Nom des départements",
	count (distinct employee_id) AS "Effectif total"
FROM employees 
JOIN departments USING(department_id)
JOIN locations  USING(location_id) 
JOIN countries USING(country_id)
JOIN regions USING(region_id)
GROUP BY
 GROUPING SETS(
        (region_name,country_name, locations.city),
        (region_name,country_name),
        (region_name),
        ()
    )
   
ORDER BY region_name,country_name, locations.city;

--Q6
SELECT
    COALESCE(region_name, 'Les '||count(DISTINCT region_id)|| ' regions: '||string_agg(DISTINCT region_name, ', ' ORDER BY region_name) ) as region,
    COALESCE (country_name, 'Les ' ||count(DISTINCT country_name)|| ' pays: '||string_agg(DISTINCT country_name, ', ' ORDER BY country_name)) as pays,
    COALESCE ( locations.city , 
			  CASE  COUNT(DISTINCT locations.city)
			  WHEN 1 THEN 'La ville: '
			  ELSE 
			  'Les '||count(DISTINCT locations.city)|| ' villes: '
			  END
			  ||string_agg(DISTINCT locations.city, ', ' ORDER BY locations.city)) as ville,
	CASE  COUNT(DISTINCT department_name)
			  WHEN 1 THEN 'Un département: '
			  ELSE 
			  count(DISTINCT department_name)|| ' départements: '
			  END
			  || string_agg( DISTINCT department_name, ' - ' ORDER BY department_name) as "Nom des départements"
FROM departments 
JOIN locations  USING(location_id) 
JOIN countries USING(country_id)
JOIN regions USING(region_id)
GROUP BY
 GROUPING SETS(
        (region_name,country_name, locations.city),
        (region_name,country_name),
        (region_name),
        ()
    )
   
ORDER BY region_name,country_name, locations.city;

--Q7
SELECT
    COALESCE(region_name, 'Les '||count(DISTINCT region_id)|| ' regions: '||string_agg(DISTINCT region_name, ', ' ORDER BY region_name) ) as region,
    COALESCE (country_name, 'Les ' ||count(DISTINCT country_name)|| ' pays: '||string_agg(DISTINCT country_name, ', ' ORDER BY country_name)) as pays,
    COALESCE ( locations.city , 
			  CASE  COUNT(DISTINCT locations.city)
			  WHEN 1 THEN 'La ville: '
			  ELSE 
			  'Les '||count(DISTINCT locations.city)|| ' villes: '
			  END
			  ||string_agg(DISTINCT locations.city, ', ' ORDER BY locations.city)) as ville,
	CASE  COUNT(DISTINCT department_name)
			  WHEN 1 THEN 'Un département: '
			  ELSE 
			  count(DISTINCT department_name)|| ' départements: '
			  END
			  || string_agg( DISTINCT department_name, ' - ' ORDER BY department_name) as "Nom des départements"
FROM departments 
JOIN locations  USING(location_id) 
JOIN countries USING(country_id)
JOIN regions USING(region_id)
GROUP BY

        CUBE(region_name,country_name, locations.city)
   
ORDER BY region_name,country_name, locations.city;

--Q8
SELECT
    first_name,
    last_name,
	department_name,
    salary,
    MAX(salary) OVER(PARTITION BY department_id) "SalaireMaximum"
FROM
    employees
JOIN departments USING(department_id);
--Q9
SELECT department_name, 
       last_name, 
	   salary,
	   LEAD (salary,1) OVER ( PARTITION BY department_name ORDER BY salary) AS next_highest_salary,
	   LAG (salary,1) OVER ( PARTITION BY department_name ORDER BY salary) AS previous_highest_salary
	   FROM employees 
       JOIN departments 
                USING(department_id )
ORDER BY department_name, 
       salary,
	   last_name;

--Q10
SELECT 
    department_name,
    first_name,
    last_name,
    salary
FROM 
    (
        SELECT 
            department_name,
            ROW_NUMBER() OVER (
                PARTITION BY department_name
                ORDER BY salary ASC) row_num, 
            first_name, 
            last_name, 
            salary
        FROM 
            employees e
            INNER JOIN departments d 
                ON d.department_id = e.department_id
    ) t
WHERE row_num = 3
ORDER BY salary DESC;

--Q11

WITH ecart as (SELECT department_name, 
       last_name, 
	   salary,
	   LAG (salary,1) OVER ( PARTITION BY department_name ORDER BY salary) AS previous_highest_salary,
	   case when (lag(salary,1) over (partition by department_name order by salary) is null)
			then 0
 			ELSE 
			salary -lag(salary,1) over (partition by department_name ORDER BY salary)
	   end 
			   as step
	   FROM employees e
       JOIN departments d 
                ON d.department_id = e.department_id )
SELECT * from ecart where step <300
ORDER BY department_name, 
       salary,
	   last_name;
--Q12
select * from salary_changes;
update employees set salary = salary +10000 where first_name='Steven' and last_name='King';
select * from salary_changes;
delete from salary_changes;

--Q13 maintenance de la table job

select jobs.*, employee_id, salary from jobs join employees using(job_id)
 where salary <jobs.min_salary or salary > jobs.max_salary
 update employees set salary=30000 where employee_id=100


--Q14 trigger pour verifier si l'update de salaire est possible

CREATE FUNCTION public.checksalary()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$DECLARE
  salairemin numeric(8,2);
 salairemax numeric(8,2);
 BEGIN
  salairemax =(SELECT max_salary from jobs where jobs.job_id=old.job_id);
  salairemin =(SELECT min_salary from jobs where jobs.job_id=old.job_id);
 
  IF NEW.salary < salairemax or NEW.salary >salairemin THEN
  RETURN NEW;
  ELSE
  RETURN OLD;
  END IF;

END;$BODY$;

CREATE TRIGGER before_update_salary
    BEFORE UPDATE 
    ON public.employees
    FOR EACH ROW
    EXECUTE PROCEDURE public.checksalary();


