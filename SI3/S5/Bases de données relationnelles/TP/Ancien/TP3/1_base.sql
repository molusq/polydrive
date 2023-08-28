DROP TABLE if EXISTS depots_new;
DROP TABLE if EXISTS depots_incorrect;
DROP TABLE if EXISTS depots_depots;
DROP VIEW if EXISTS dep;
DROP VIEW if EXISTS dep2;

CREATE TABLE depots_new (
        nom VARCHAR(30),
        classe INT,
        pays VARCHAR(50),
        num_dep INT,
		date_depot DATE
        );

\copy depots_new from 'depots_init'

/*CREATE TABLE depots_incorrect (
        nom VARCHAR(30),
        classe INT,
        pays VARCHAR(50),
        num_dep INT,
		date_depot DATE
        );*/

/*CREATE VIEW dep AS (
SELECT 1 d1.num_dep, d1.pays, d2.num_dep, d2.pays
FROM depots_new d1, depots_new d2
WHERE d1.pays = d2.pays
AND d1.date_depot > d2.date_depot
AND d1.num_dep < d2.num_dep);*/

CREATE VIEW dep2 AS(
SELECT d1.nom, d1.classe, d1.pays, d1.num_dep, d1.date_depot
	FROM depots_new d1, depots_new d2
	WHERE d1.pays = d2.pays
	AND d1.date_depot > d2.date_depot
	AND d1.num_dep < d2.num_dep
UNION ALL
	SELECT d2.nom, d2.classe, d2.pays, d2.num_dep, d2.date_depot
	FROM depots_new d1, depots_new d2
	WHERE d1.pays = d2.pays
	AND d1.date_depot > d2.date_depot
	AND d1.num_dep < d2.num_dep);

--copy depots_incorrect from 'dep2';
SELECT  *
INTO    depots_incorrect
FROM    dep2;
--INSERT INTO depots_incorrect(nom, classe, pays, num_dep, date_depot)
--SELECT nom, classe, pays, num_dep, date_depot FROM dep;
--\copy dep from 'depots_new'


CREATE TABLE depots (
        nom VARCHAR(30)  NOT NULL,
        classe INT  NOT NULL REFERENCES classe,
        pays CHAR(2)  NOT NULL REFERENCES pays,
        id  INT  NOT NULL PRIMARY KEY,
		date_depot DATE NOT NULL
        );

SELECT *
INTO depots
FROM depots_new
WHERE nom not in (select nom from dep2);