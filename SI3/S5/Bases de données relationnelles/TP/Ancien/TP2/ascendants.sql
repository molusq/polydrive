-- Recherche des ascendants

DROP TABLE IF EXISTS  Parents;
CREATE TABLE Parents
( Pere CHAR(10),
  Mere CHAR(10),
  Enfant CHAR(10));

-- population de la table
INSERT INTO Parents VALUES ('Clara', 'Jean', 'Paul');
INSERT INTO Parents VALUES ('Jean', 'Nadia', 'Claire');
INSERT INTO Parents VALUES ('Blandine', 'Paul', 'Zoee');
INSERT INTO Parents VALUES ('Claire', 'Zoile', 'Louis');
INSERT INTO Parents VALUES ('Zoee', 'Yvan', 'Julia');
INSERT INTO Parents VALUES ('Louis', 'Julia', 'Adrien');


/*
         Blandine 
                  \   Zoee
Clara            /        \  Julia
       \    Paul           /       \
       /              Yvan         /  Adrien
Jean                             /
     \   Claire                /
     /         \    Louis    /
Nadia          / 
         Zoile
*/

WITH RECURSIVE ancetre(Aieul, Enfant) AS 
	(SELECT Pere, Enfant FROM Parents
	UNION 
	SELECT Mere, Enfant FROM Parents
	UNION  
	SELECT A.Aieul, P.Enfant 
			FROM 	Parents AS P, 
				ancetre AS A 
				WHERE P.Pere =A.Enfant OR  P.Mere =A.Enfant )
SELECT * FROM ancetre where Enfant='Julia';
/*
 aieul    |   enfant   
------------+------------
 Yvan       | Julia     
 Zoee       | Julia     
 Paul       | Julia     
 Blandine   | Julia     
 Jean       | Julia     
 Clara     | Julia    

*/

DROP VIEW IF EXISTS  ascendant;
CREATE View ascendant(ascendant,enfant) AS (
WITH RECURSIVE ancetre(Aieul, Enfant) AS 
	(SELECT Pere, Enfant FROM Parents
	UNION 
	SELECT Mere, Enfant FROM Parents
	UNION  
	SELECT A.Aieul, P.Enfant 
			FROM 	Parents AS P, 
				ancetre AS A 
				WHERE P.Pere =A.Enfant OR  P.Mere =A.Enfant )
SELECT * FROM ancetre);


SELECT * FROM ascendant where Enfant='Julia';
/*
 ascendant  |   enfant   
------------+------------
 Yvan       | Julia     
 Zoee       | Julia     
 Paul       | Julia     
 Blandine   | Julia     
 Jean       | Julia     
 Clara      | Julia     
(6 rows)
*/