
-- TP2 :   Partie 2 : verification de contraintes


-- Verification que les marques referencees dans les depots existent bien
-- Verification que les groupes references dans les depots existent bien
-- Verification que les depots references dans les enregistrements existent bien
--
-- Ces verifications sont effectuees automatiquement si vous utilisez une cle 
-- externe (xx references yy)


-- Verification que La numerotation des depots est chronologique pour chaque pays

SELECT  D1.NumLegal, D1.Pays, D2.NumLegal, D2.Pays
FROM   Depots D1, Depots D2
WHERE  D1.Pays = D2.Pays
  AND  D2.DateDepot > D1.DateDepot
  AND  D2.NumLegal < D1.NumLegal;


-- Verification que La numerotation des enregistrements est globale pour chaque pays
-- (verifiee par construction si ces deux attributs constituent la clé de la table)

SELECT  E1.NumLegal, E1.Pays, E2.NumLegal, E2.Pays
FROM   Enregistrements E1, Enregistrements E2
WHERE  E1.Pays = E2.Pays
  AND  E1.NumLegal = E2.NumLegal
  AND  E1.NumDepot != E2.NumDepot;



