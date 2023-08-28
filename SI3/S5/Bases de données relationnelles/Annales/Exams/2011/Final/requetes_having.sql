

-- 1 Les  noms des magasins, montants et dates pour les ventes de plus de 1000 euros effectuees apres le 15 janvier 1995

SELECT magasin, montant, laDate
      FROM ventes
      WHERE laDate > '15-Jan-1995' AND montant > 1000;


-- 2 la somme totale des montants des ventes par magasin

SELECT magasin, SUM(montant)
      FROM ventes
      GROUP BY magasin;


-- 3 la somme totale des ventes par magasin pour les magasins qui ont effectue des ventes dont le total depasse 2000 euros


SELECT magasin, SUM(montant)
      FROM ventes
      GROUP BY magasin
      HAVING SUM(montant) > 2000;

 
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



--5 la somme  des ventes de plus de 2000 euro par magasin pour les magasins qui ont effectue au moins une vente de 
-- plus de 2000 euros

SELECT magasin, SUM(montant)
      FROM ventes 
           WHERE  montant > 2000
      GROUP BY magasin
      HAVING magasin in  (SELECT magasin            -- Remarque:Having inutile car le where implique le having                   
                                 FROM ventes WHERE montant > 2000);   


/
-- 6 la somme  des ventes  pour les magasins qui ont effectue au moins 5000 euro de vente et dont le montant par 
-- vente est d'au moins 1000 euros


SELECT magasin, SUM(montant)
      FROM ventes v1
           WHERE NOT EXISTS (SELECT * FROM ventes v2 WHERE v1.magasin=v2.magasin AND v2.montant < 1000)
      GROUP BY magasin
      HAVING SUM(montant) > 5000;
