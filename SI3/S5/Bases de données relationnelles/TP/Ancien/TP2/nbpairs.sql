-- Calcul  des nombres pairs inferieurs ou égal a 100
WITH RECURSIVE t(n) AS (
    SELECT 2  -- VALUES (2) 
  UNION
    SELECT n+2 FROM t WHERE n < 100
)
SELECT n FROM t;

/*
  n  
-----
   2
   4
   6
   8
   ..
   100
*/
 SELECT count(*), sum(n), max(n), min(n) FROM t;
/*
sum  | max | min 
------+-----+-----
 2550 | 100 |   2
(1 ligne)
*/
-- SELECT COUNT(*) FROM t GROUP by n;
/*
     1
...
     1
     1
     1
(50 rows)
*/

--- Avec une vue
CREATE VIEW view_t(n) AS (
       WITH RECURSIVE t(n) AS
       ( SELECT 2  -- VALUES (2) 
  	UNION
    	SELECT n+2 FROM t WHERE n < 100)
	SELECT n FROM t
	);
SELECT count(*), sum(n), max(n), min(n) FROM view_t;
