DROP TABLE ventes;
CREATE Table ventes (
       magasin VARCHAR,
       montant INT,
       laDate DATE);


INSERT INTO ventes VALUES('LaBoutique',2400,'14-Jan-1995');
INSERT INTO ventes VALUES('LaCave',1500,'14-Jan-1995'); 
INSERT INTO ventes VALUES('LeGrenier',500,'17-Jan-1995'); 
INSERT INTO ventes VALUES('LaBoutique',1100,'16-Jan-1995'); 
INSERT INTO ventes VALUES('LaCave',800,'17-Jan-1995'); 
INSERT INTO ventes VALUES('LeGrenier',400,'18-Jan-1995'); 
INSERT INTO ventes VALUES('LaBoutique',400,'24-Jan-1995');
INSERT INTO ventes VALUES('LaBoutique',2300,'25-Jan-1995');
INSERT INTO ventes VALUES('LeLuxe',1300,'26-Jan-1995');
INSERT INTO ventes VALUES('LeLuxe',1500,'26-Jan-1995');
INSERT INTO ventes VALUES('LeLuxe',1500,'26-Jan-1995');
INSERT INTO ventes VALUES('LeLuxe',1000,'26-Jan-1995');

-- 1 Les  noms des magasins, montants et dates pour les ventes de plus de 1000 euros effectuees apres le 15 janvier 1995

SELECT magasin, montant, laDate
      FROM ventes
      WHERE laDate > '15-Jan-1995' AND montant > 1000;

/*
 magasin   | montant |   ladate   
------------+---------+------------
 LaBoutique |    1100 | 1995-01-16
 LaBoutique |    2300 | 1995-01-25
 LeLuxe     |    1300 | 1995-01-26
 LeLuxe     |    1500 | 1995-01-26
 LeLuxe     |    1500 | 1995-01-26
(5 rows)
*/

-- 2 la somme totale des montants des ventes par magasin

SELECT magasin, SUM(montant)
      FROM ventes
      GROUP BY magasin;
/*
  magasin   | sum  
------------+------
 LaBoutique | 6200
 LaCave     | 2300
 LeLuxe     | 5300
 LeGrenier  |  900
(4 rows)
*/

-- 3 la somme totale des ventes par magasin pour les magasins qui ont effectue des ventes dont le total depasse 2000 euros


SELECT magasin, SUM(montant)
      FROM ventes
      GROUP BY magasin
      HAVING SUM(montant) > 2000;
/*
 magasin   | sum  
------------+------
 LaBoutique | 6200
 LaCave     | 2300
 LeLuxe     | 5300
(3 rows)
*/
 
-- 4 la somme totale des ventes par magasin pour les magasins qui ont effectue plus de 2000 euros de ventes apres 
-- le 15 janvier 1995

SELECT magasin, SUM(montant)
      FROM ventes
      GROUP BY magasin
      HAVING magasin in  (SELECT magasin
                                 FROM ventes
                                 WHERE laDate > '15-Jan-1995'
                                 GROUP BY magasin
                           HAVING SUM(montant) > 2000);

/*
 
magasin   | sum  
------------+------
 LaBoutique | 6200
 LeLuxe     | 5300
(2 rows)
*/

--5 la somme  des ventes de plus de 2000 euro par magasin pour les magasins qui ont effectue au moins une vente de 
-- plus de 2000 euros

SELECT magasin, SUM(montant)
      FROM ventes 
           WHERE  montant > 2000
      GROUP BY magasin
      HAVING magasin in  (SELECT magasin            -- Remarque:Having inutile car le where implique le having                   
                                 FROM ventes WHERE montant > 2000);   


/*
  magasin   | sum  
------------+------
 LaBoutique | 4700
(1 row)


*/

-- 6 la somme  des ventes  pour les magasins qui ont effectue au moins 5000 euro de vente et dont le montant par 
-- vente est d'au moins 1000 euros


SELECT magasin, SUM(montant)
      FROM ventes v1
           WHERE magasin NOT IN (SELECT magasin FROM ventes  WHERE montant < 1000)
      GROUP BY magasin
      HAVING SUM(montant) > 5000;

-- ou

SELECT magasin, SUM(montant)
      FROM ventes
      GROUP BY magasin
      HAVING SUM(montant) > 5000 and magasin NOT IN  (SELECT magasin FROM ventes  WHERE montant < 1000);

-- ou 

SELECT magasin, SUM(montant) 
      FROM ventes
      GROUP BY magasin
      HAVING SUM(montant) > 5000 and MIN(montant) >= 1000;

/*
magasin | sum  
---------+------
 LeLuxe  | 5300
(1 row)
*/

