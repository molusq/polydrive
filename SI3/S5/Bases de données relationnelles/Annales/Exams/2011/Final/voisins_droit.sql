

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
INSERT INTO voisin_droit VALUES('mathilde','francois');
INSERT INTO voisin_droit VALUES('mathilde','rose');
INSERT INTO voisin_droit VALUES('rose','bastien');
INSERT INTO voisin_droit VALUES('rose','rudolph');

-- 1) nom d'une personne et celui de chacun de ses voisins droits 

WITH RECURSIVE Lesvoisins(nom, voisins) AS (
          SELECT  nom ,voisin_d  FROM  voisin_droit 
          UNION
          SELECT v.nom, vs.voisins
          FROM voisin_droit v,  Lesvoisins vs
          WHERE v.voisin_d = vs.nom)
        SELECT nom, voisins FROM Lesvoisins; 

-- 2) nom d'une personne et celui de chacun de ses voisins droits ainsi que leur distance

WITH RECURSIVE Lesvoisins(nom, voisins, N) AS (
          SELECT  nom ,voisin_d,1  FROM  voisin_droit 
          UNION
          SELECT v.nom, vs.voisins, vs.N+1
          FROM voisin_droit v,  Lesvoisins vs
          WHERE v.voisin_d = vs.nom)
        SELECT nom, voisins, N FROM Lesvoisins;
  

-- 3) nom d'une personne et celui de chacun de ses voisins droits a une distance > 3


WITH RECURSIVE Lesvoisins(nom, voisins, N) AS (
          SELECT  nom ,voisin_d,1  FROM  voisin_droit 
          UNION
          SELECT v.nom, vs.voisins, vs.N+1
          FROM voisin_droit v,  Lesvoisins vs
          WHERE v.voisin_d = vs.nom)
        SELECT nom, voisins, N FROM Lesvoisins where N >2;
  
-- 4) Noms des voisins droits les plus éloignés


WITH RECURSIVE Lesvoisins(nom, voisins, N) AS (
          SELECT  nom ,voisin_d, 1  FROM  voisin_droit 
          UNION
          SELECT v.nom, vs.voisins, vs.N+1
          FROM voisin_droit v,  Lesvoisins vs
          WHERE v.voisin_d = vs.nom)
        SELECT nom, voisins, N FROM Lesvoisins      
              where N = (SELECT  MAX(N) FROM Lesvoisins); 
 	      -- ou where N >= ALL (SELECT  N FROM Lesvoisins); 

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

