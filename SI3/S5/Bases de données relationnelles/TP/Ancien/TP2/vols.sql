\i data_vols.sql


-- Recherche des vols dont la duree de vol est superieure a 10h

WITH RECURSIVE reaches(departure,totaltime, arrival) AS 
	(SELECT departure, atime-dtime, arrival FROM vols 
	UNION  --ALL
	SELECT R1.departure,  R1.atime-R1.dtime + R2.totaltime, R2.arrival 
			FROM 	vols AS R1, 
				reaches AS R2 
				WHERE R1.arrival =R2.departure
	)
SELECT * FROM reaches where totaltime > '10:00';

/*
 departure | totaltime | arrival 
-----------+-----------+---------
 SF        | 10:30:00  | NY 
 SF        | 11:00:00  | NY 
(2 rows)

*/

-- Recherche des vols avec le nombre d'escales

WITH RECURSIVE reaches1(departure,escales, arrival) AS 
	(SELECT departure, 0, arrival FROM vols 
	UNION  --ALL
	SELECT R1.departure,  1 + R2.escales, R2.arrival 
			FROM 	vols AS R1, 
				reaches1 AS R2 
				WHERE R1.arrival =R2.departure
	)
SELECT * FROM reaches1 where escales > 1;

/*
 departure | escales | arrival 
-----------+---------+---------
 SF        |       2 | NY 
 DEN       |       2 | NY 
 SF        |       2 | CHI
 SF        |       3 | NY 
(4 rows)

*/

