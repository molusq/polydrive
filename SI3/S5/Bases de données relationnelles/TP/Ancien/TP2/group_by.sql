DROP TABLE IF EXISTS si4;
CREATE TABLE si4
(
groupe int,
nom  char(10),
ddn date
);
INSERT INTO si4 VALUES(1, 'Paul','1989-12-09');
INSERT INTO si4 VALUES(1, 'Pierre','1991-12-09');
INSERT INTO si4 VALUES(2, 'Paul','1988-12-09');
INSERT INTO si4 VALUES(2, 'Pierre','1989-12-09');
INSERT INTO si4 VALUES(2, 'Jean','1987-12-09');
INSERT INTO si4 VALUES(3, 'Paul','1988-12-09');

-- Calculer pour chaque groupe  le nombre d'étudiants
SELECT   groupe,
        COUNT(*)          -- compte les étudiants par groupe
,        MIN(ddn)         -- date de naissance du plus vieil étudiant par groupe
,        MAX(ddn)         -- date de naissance du plus jeune étudiant par groupe
FROM     si4
GROUP BY groupe;

/*

 groupe | count |    min     |    max     
--------+-------+------------+------------
      1 |     2 | 1989-12-09 | 1991-12-09
      3 |     1 | 1988-12-09 | 1988-12-09
      2 |     3 | 1987-12-09 | 1989-12-09
(3 rows)

*/
