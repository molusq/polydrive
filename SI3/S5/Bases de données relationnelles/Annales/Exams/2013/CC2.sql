

DROP TABLE  IF EXISTS Employes CASCADE;
CREATE TABLE Employes
        ( 
	Nom  VARCHAR(30),
	Prenom VARCHAR(30), 
	DateDeNaissance DATE, 
	Adresse VARCHAR(30),
	NumSS INTEGER PRIMARY KEY,
	Salaire INTEGER, 
	NumeroDepartement INTEGER, 
	Superieur INTEGER REFERENCES Employes
	 );

INSERT INTO Employes (Nom, Prenom, DateDeNaissance, NumSS, Superieur)  VALUES ('Big',  'Boss', '1961-01-10', '16101', '16101');
INSERT INTO Employes (Nom, Prenom, DateDeNaissance, NumSS, Superieur)  VALUES ('Rochat',  'Juliette', '1990-10-10', '29010', '16101');
INSERT INTO Employes (Nom, Prenom, DateDeNaissance, NumSS, Superieur)  VALUES ('Durand',  'Paul', '1950-09-10', '14509', '29010');
INSERT INTO Employes (Nom, Prenom, DateDeNaissance, NumSS, Superieur)  VALUES ('Dumond',  'Pierre', '1989-11-11', '19011', '16101');
INSERT INTO Employes (Nom, Prenom, DateDeNaissance, NumSS, Superieur)  VALUES ('Martin',  'Lisa', '1999-12-12', '25912', '14509');
INSERT INTO Employes (Nom, Prenom, DateDeNaissance, NumSS, Superieur)  VALUES ('Dufoux',  'Paul', '1945-10-10', '19510', '25912');

-- Q1:  les noms de tous les employes  dont le superieur hierachique (direct ou indirect)  est   Rochat
WITH RECURSIVE sups_jr(Nom, NumSS) AS 
	(
	SELECT Nom, NumSS FROM  Employes E1 WHERE E1.Superieur IN 
                   (SELECT E2.NumSS FROM  Employes E2 WHERE E2.Nom='Rochat' AND E2.Prenom = 'Juliette')
	UNION  ALL
	SELECT E3.Nom,E3.NumSS FROM  Employes AS E3,  sups_jr AS S
	       WHERE S.NumSS =E3.Superieur
	 )
SELECT * FROM sups_jr;
/*
  nom   | numss 
--------+-------
 Durand | 14509
 Martin | 25912
 Dufoux | 19510
(3 rows)
*/

-- Q2: les noms de tous les employes  dont le superieur hierachique est  Juliette Rochat et qui sont plus ages qu'elle
DROP VIEW  IF EXISTS oldsups_jul;
CREATE  VIEW   oldsups_jul(Nom, NumSS,DateDeNaissance) 
 AS WITH RECURSIVE oldsups_jr(Nom, NumSS,DateDeNaissance) AS 
	(
	SELECT Nom, NumSS,DateDeNaissance  FROM  Employes E1 WHERE E1.Superieur IN 
                   (SELECT E2.NumSS FROM  Employes E2 WHERE E2.Nom='Rochat' AND E2.Prenom = 'Juliette')
	UNION  ALL
	SELECT E3.Nom,E3.NumSS,E3.DateDeNaissance  FROM  Employes AS E3,  oldsups_jr AS S
	       WHERE S.NumSS =E3.Superieur
	 )
 SELECT * FROM oldsups_jr;


SELECT Nom AS Olds, DateDeNaissance FROM oldsups_jul E1  WHERE  E1.DateDeNaissance <  
        (SELECT E2.DateDeNaissance FROM  Employes E2 WHERE E2.Nom='Rochat' AND E2.Prenom = 'Juliette');
/*
/*
  olds  | datedenaissance 
--------+-----------------
 Durand | 1950-09-10
 Dufoux | 1945-10-10
(2 rows)
*/



DROP TABLE  IF EXISTS t1;
CREATE TABLE t1
        ( Num INT,
	  Nom CHAR(1));
DROP TABLE  IF EXISTS t2;
CREATE TABLE t2
        ( Num INT,
	  Nom CHAR(1));
INSERT INTO t1 VALUES (1,'a');
INSERT INTO t1 VALUES (2,'b');
INSERT INTO t1 VALUES (3,'x');
INSERT INTO t2 VALUES (1,'a');
INSERT INTO t2 VALUES (2,'b');
INSERT INTO t2 VALUES (3,'y');
SELECT * FROM t1 NATURAL JOIN t2;
SELECT * FROM t1 LEFT JOIN t2 USING(num,nom);
SELECT * FROM t1 RIGHT JOIN t2 USING(num,nom);
SELECT * FROM t1 FULL JOIN t2 USING(num,nom);

/*
 num | nom 
-----+-----
   1 | a
   2 | b
(2 rows)

 num | nom 
-----+-----
   1 | a
   2 | b
   3 | x
(3 rows)

 num | nom 
-----+-----
   1 | a
   2 | b
   3 | y
(3 rows)

 num | nom 
-----+-----
   1 | a
   2 | b
   3 | x
   3 | y
(4 rows)

*/
*/
