

DROP  TABLE voisin_droit CASCADE;
CREATE TABLE voisin_droit(
          nom  VARCHAR,
          voisin_d VARCHAR
        );

INSERT INTO voisin_droit VALUES('pierre','francois');
INSERT INTO voisin_droit VALUES('pierre','jean');
INSERT INTO voisin_droit VALUES('andre','rose');
INSERT INTO voisin_droit VALUES('jean','bastien');
INSERT INTO voisin_droit VALUES('jean','rudolph');
INSERT INTO voisin_droit VALUES('rudolph','gwendoline');
INSERT INTO voisin_droit VALUES('Monique','andre');

DROP  TABLE voisin_gauche CASCADE;
CREATE TABLE voisin_gauche(
          nom  VARCHAR,
          voisin_g  VARCHAR
        );

INSERT INTO voisin_gauche VALUES('mathilde','francois');
INSERT INTO voisin_gauche VALUES('mathilde','rose');
INSERT INTO voisin_gauche VALUES('rose','bastien');
INSERT INTO voisin_gauche VALUES('rose','rudolph');

-- Vue qui définie les voisins

CREATE  OR REPLACE  view voisins(nom, voisin) as 
   SELECT nom,  voisin_d from voisin_droit
   UNION
   SELECT nom,  voisin_g from voisin_gauche;

--calcul recursif de l'ensemble des voisins 

WITH RECURSIVE Lesvoisins(nom, voisins) AS (
          SELECT  nom ,voisin  FROM  voisins 
          UNION
          SELECT v.nom, vs.voisins
          FROM voisins v,  Lesvoisins vs
          WHERE v.voisin = vs.nom)
        SELECT nom, voisins FROM Lesvoisins; 

-- calcul recursif  des voisins a une distance > 3


WITH RECURSIVE Lesvoisins(nom, voisins, N) AS (
          SELECT  nom ,voisin,1  FROM  voisins 
          UNION
          SELECT v.nom, vs.voisins, vs.N+1
          FROM voisins v,  Lesvoisins vs
          WHERE v.voisin = vs.nom)
        SELECT nom, voisins, N FROM Lesvoisins where N >2;
  
-- calcul recursif  des voisins les plus éloignés


WITH RECURSIVE Lesvoisins(nom, voisins, N) AS (
          SELECT  nom ,voisin,1  FROM  voisins 
          UNION
          SELECT v.nom, vs.voisins, vs.N+1
          FROM voisins v,  Lesvoisins vs
          WHERE v.voisin = vs.nom)
        SELECT L1.nom, L1.voisins, L1.N FROM Lesvoisins L1 
              where N >= ALL (SELECT  L2.N FROM Lesvoisins L2); 
/*

   nom    |  voisins   
----------+------------
 rose     | bastien
 jean     | rudolph
 pierre   | francois
 Monique  | andre
 pierre   | jean
 rudolph  | gwendoline
 mathilde | rose
 andre    | rose
 mathilde | francois
 jean     | bastien
 rose     | rudolph
 Monique  | rose
 pierre   | bastien
 pierre   | rudolph
 mathilde | bastien
 mathilde | rudolph
 andre    | bastien
 andre    | rudolph
 jean     | gwendoline
 rose     | gwendoline
 Monique  | bastien
 Monique  | rudolph
 pierre   | gwendoline
 mathilde | gwendoline
 andre    | gwendoline
 Monique  | gwendoline
(26 rows)

   nom    |  voisins   | n 
----------+------------+---
 Monique  | bastien    | 3
 Monique  | rudolph    | 3
 pierre   | gwendoline | 3
 mathilde | gwendoline | 3
 andre    | gwendoline | 3
 Monique  | gwendoline | 4
(6 rows)

   nom   |  voisins   | n 
---------+------------+---
 Monique | gwendoline | 4
(1 row)

*/

