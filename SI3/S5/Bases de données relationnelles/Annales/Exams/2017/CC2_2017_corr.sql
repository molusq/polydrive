
-- 1 Les noms et pays des marques enregistrées avant le ’1990-05-01’, classées par noms.
select M.nom,M.pays  from marque M
       where  exists (select * from enr  E where M.Id=E.marque and E.date_enr < '1990-05-01')
       order by M.nom;

--2 Les marques avec leur nombre de classes; on affichera le nom de la marque, son pays et son nombre de classes.
select M.nom, M.pays, count(*) AS NB from  marque M
       GROUP by M.nom,M.pays
       ORDER by 1,2;

--3 Les noms des marques avec plus de deux classes;on affichera le nom de la marque, son pays et son nombre de classes.
select M.nom,M.pays, count(*) AS NB from  marque M
       GROUP by M.nom,M.pays
       HAVING COUNT(*) > 2
       ORDER by 1,2;

--4 Le nom, les pays et l’Id des sociétés dont le nom commence par ’ORACLE’ et dont les pays sont différents; on n’affichera pas plusieurs fois le même résultat.
-- Exemple:

--       nom      | pays1 | pays2 | id
--  --------------+-------+-------+----
--  ORACLE Gmbh.  | DE    | UK    | 12
--  ORACLE Gmbh.  | DE    | US    | 12
--  ORACLE Ltd.   | UK    | US    | 12

select S1.nom,S1.pays as Pays1, S2.pays as Pays2, S1.id from societe S1, societe S2
       where S1.nom LIKE 'ORACLE%' and S2.nom  LIKE 'ORACLE%' and S1.pays < S2.pays
       order by S1.nom, S1.pays, S2.pays, S1.id;

--5  Les identifiants des vendeurs qui ont vendu plus d’une marque après la date ’1987-01-01’.

 select V2.vendeur from vente V2
 	WHERE date_vente > '1987-01-01'
 	GROUP BY V2.vendeur
	HAVING count(*) >1;


-- 6

-- Soit les tables :
-- crayons(id_objet char(10), prix INTEGER , couleur VARCHAR(20)
-- proprio (id_objet char(10), prop VARCHAR(20));

DROP TABLE IF EXISTS crayons, proprio;
CREATE Table crayons(id_objet char(10),
	Prix INTEGER ,
	Couleur VARCHAR(20));

CREATE Table proprio (id_objet  char(10),
	prop  VARCHAR(20));
INSERT INTO crayons VALUES ('stylo','1','vert');
INSERT INTO crayons VALUES ('feutre','2','jaune');
INSERT INTO proprio VALUES ('stylo','pierre');
INSERT INTO proprio VALUES ('crayon','paul');

-- a) Affichez toutes les informations disponibles (id_objet,prix, couleur, proprio) pour tous
-- les objets dans l’une ou l’autre table.

SELECT * FROM  crayons FULL JOIN proprio using(id_objet);

-- b) Affichez toutes les informations disponibles (id_objet,prix, couleur, proprio) pour tous les objets de la
-- table crayons.

SELECT * FROM  crayons LEFT JOIN proprio using(id_objet);

-- 7
DROP TABLE IF EXISTS vol;
CREATE TABLE vol (
       departure char(3),
       arrival char(3)) ;


INSERT INTO vol VALUES ('DEN','NY');
INSERT INTO vol VALUES ('CHI','DEN');
INSERT INTO vol VALUES ('CHI','NY');
INSERT INTO vol VALUES ('CHI','BOS');
INSERT INTO vol VALUES ('DAL','CHI');
INSERT INTO vol VALUES ('SF','DAL');
INSERT INTO vol VALUES ('BOS','SF');
INSERT INTO vol VALUES ('SF','NY');
INSERT INTO vol VALUES ('NY','DAL');




--7 Ecrire une requête en SQL qui recherche tous les vols de moins de 4 escales tels que :
-- – soit le dernier vol est un vol direct de SF à NY,
-- – soit les deux derniers vols sont : un vol direct de SF à DAL suivi d’un vol direct de DAL à CH.

WITH RECURSIVE reaches(departure,Nb,escales, arrival) AS 
	(
	SELECT departure,0,'', arrival FROM vol WHERE departure='SF' AND arrival = 'NY'
	UNION
	SELECT	R1.departure,1, 'DAL', R2.arrival 
		FROM  	vol AS R1, 	vol AS R2 
		WHERE  R1.departure='SF' AND  R1.arrival='DAL' and  R2.departure='DAL' and R2.arrival='CHI'
	UNION
	SELECT	R1.departure,1+R2.Nb,  R1.arrival ||'-'|| R2.escales, R2.arrival 
			FROM  	vol AS R1, 
				reaches AS R2 
				WHERE R1.arrival =R2.departure  AND Nb < 3			
	)
SELECT  departure,Nb,escales, arrival FROM reaches ; 
		

