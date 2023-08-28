-- 7. Les marques homonymes (meme nom et classe) de pays differents et
--    de proprietaires differents


CREATE TABLE marque (
	id  INT, 
	nom VARCHAR(30),
	classe INT,
	pays CHAR(2),
	prop INT
	);

SELECT M1.nom AS NOM_M, M1.classe AS CLASSE,  M1.pays AS PAYS_M1,
 M1.prop AS PROP1, M2.pays AS PAYS_M2,  M2.prop AS PROP2 
FROM marque M1,  marque M2
WHERE  M1.nom =M2.nom AND M1.classe=M2.classe AND M1.prop<>M2.prop 
       AND   M1.pays<M2.pays ;

/*
Utilité dans le where de  la clause M1.pays<M2.pays ?
Cocher toutes les propositions exactes:
- Eviter d'avoir deux fois le même pays dans la liste des marques
- Afficher les marques dans l'ordre alphabétique des pays
- Eliminer les  symétries dans les réponses
- Eliminer les doublons (on pourrait la remplacer par SELECT DISTINCT)
- Aucune

*/

/* 
Ecrire une requête qui affiche par classe:
- le numéro de la classe
- les numéro des propriétaires (ordonnés de manière croissante) qui possèdent plus de 4 marques dans la classe
- le nombre de marque que possède chaque propriétaire

*/
SELECT M.classe, M.prop, count(*) FROM marque M
       GROUP by classe, prop 
       HAVING count(*) > 4
       ORDER by 2;

/*
 classe | prop | count 
--------+------+-------
      4 |    1 |    10
      4 |    3 |     5
      6 |    9 |     5
      1 |   20 |     7
     11 |   21 |     8
      1 |   26 |     8
     10 |   27 |     8
     10 |   31 |     7
      4 |   46 |     7
      4 |   47 |     5
      4 |   48 |     6
(11 lignes)

*/
CREATE TABLE TA (
       N1 INTEGER,
       N2 INTEGER,
       C CHAR(1) );
insert into TA (C) values ('a');
insert into TA (N1,C) values (1,'b');
insert into TA (N2,C) values (2,'c');
insert into TA  values (1,2,'c');

/*
rueher=# select * from TA;
 n1 | n2 | c 
----+----+---
    |    | a
  1 |    | b
    |  2 | c
  1 |  2 | c
(4 lignes)



/* 
Question 1

Réponse à la requête:


*/
 SELECT C  FROM TA
 	  WHERE   N1 >=0 AND N2 >= 0; 
/*
 C 
---
c
(1 lignes)

ou a, b, c, c ?
ou b,c,c?
ou c ?

*/

/* 
Question 1

Réponse à la requête:


*/
 SELECT  N1  FROM TA
 	 EXCEPT
 SELECT  N2  FROM TA;

/*
 n1 
----
  1
(1 ligne)
ou

 n1 
----
  1
  1
(2 ligne)

*/


