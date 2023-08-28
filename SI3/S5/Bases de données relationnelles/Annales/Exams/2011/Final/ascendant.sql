

DROP  TABLE pere CASCADE;
CREATE TABLE pere(
          nom  VARCHAR,
          enfant VARCHAR
        );

INSERT INTO pere VALUES('pierre','francois');
INSERT INTO pere VALUES('pierre','jean');
INSERT INTO pere VALUES('andre','rose');
INSERT INTO pere VALUES('jean','bastien');
INSERT INTO pere VALUES('jean','rudolph');
INSERT INTO pere VALUES('rudolph','gwendoline');
INSERT INTO pere VALUES('Monique','andre');

DROP  TABLE mere CASCADE;
CREATE TABLE mere(
          nom  VARCHAR,
          enfant  VARCHAR
        );

INSERT INTO mere VALUES('mathilde','francois');
INSERT INTO mere VALUES('mathilde','rose');
INSERT INTO mere VALUES('rose','bastien');
INSERT INTO mere VALUES('rose','rudolph');

-- Vue qui définie les parents

CREATE  OR REPLACE  view parents(Parent, Enfant) as 
   SELECT Nom, Enfant from pere
   UNION
   SELECT Nom, Enfant from mere;

-- calcul recursif de l'ensemble des descendants 

WITH RECURSIVE descendants(Ascendant, Descendant) AS (
          SELECT  Parent,Enfant  FROM  PARENTS 
          UNION
          SELECT p.Parent, d.Descendant
          FROM parents p, descendants d
          WHERE p.Enfant = d.Ascendant)
        SELECT Ascendant, Descendant FROM descendants; 

-- calcul recursif  des descendants a une distance > 3

WITH RECURSIVE descendants(Ascendant, Descendant, N) AS (
          SELECT  Parent,Enfant, 1 FROM  PARENTS 
          UNION
          SELECT p.Parent, d.Descendant, d.N+1
          FROM parents p, descendants d
          WHERE p.Enfant = d.Ascendant)
        SELECT Ascendant, Descendant, N FROM descendants  where N >2; 


/*

 ascendant | descendant 
-----------+------------
 rose      | bastien
 jean      | rudolph
 pierre    | francois
 Monique   | andre
 pierre    | jean
 rudolph   | gwendoline
 mathilde  | rose
 andre     | rose
 mathilde  | francois
 jean      | bastien
 rose      | rudolph
 Monique   | rose
 pierre    | bastien
 pierre    | rudolph
 mathilde  | bastien
 mathilde  | rudolph
 andre     | bastien
 andre     | rudolph
 jean      | gwendoline
 rose      | gwendoline
 Monique   | bastien
 Monique   | rudolph
 pierre    | gwendoline
 mathilde  | gwendoline
 andre     | gwendoline
 Monique   | gwendoline
(26 rows)

 ascendant | descendant | n 
-----------+------------+---
 Monique   | bastien    | 3
 Monique   | rudolph    | 3
 pierre    | gwendoline | 3
 mathilde  | gwendoline | 3
 andre     | gwendoline | 3
 Monique   | gwendoline | 4
(6 rows)

*/

