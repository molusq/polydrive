
-- TP2:  Phase 4


DROP SEQUENCE IF EXISTS  seq_idm;
DROP TABLE IF EXISTS tmp_grp; 
DROP SEQUENCE IF EXISTS seq_groupe;


-- Etape 4 : remplissage des tables

-- Suppression des anciennes valeurs 
delete from depots;
delete from marque_new;
delete from groupes_societes;
delete from groupes;


-- Groupes:
-- creation de la sequence des groupes 

CREATE SEQUENCE seq_groupe START  1 INCREMENT  1;

-- creation d'une table temporaire tel que (num_dep,pays)<-> idg
-- (il s'agit d'un majorant du nombre de groupes)

CREATE TEMPORARY TABLE tmp_grp  
(	idg     INT NOT NULL,
	ndep    INT NOT NULL, -- ndep et pays : utiles pour l'identification 
                              -- d'un dépot
        Pays 	varchar(50) NOT NULL
);

INSERT INTO  tmp_grp 
       SELECT  nextval('seq_groupe'),D.num_dep, D.pays
       FROM (SELECT  num_dep, pays
		     FROM depots_init1
		     GROUP BY num_dep, pays) AS D;


INSERT INTO  Groupes SELECT idg FROM tmp_grp; 
--INSERT 0 77

-- Societes : les societés qui font des dépots doivent exister 
-- dans la table société

-- Groupes_societes
INSERT INTO Groupes_societes (IdGroupe, IdSociete)
	SELECT G.idg, T.deposant
	FROM  tmp_grp AS G, deposants_init AS T
	WHERE  G.ndep = T.num_dep 
			AND G.pays = T.pays;
--INSERT 0 79

-- Marques
-- on cree une sequence des id. de marque pour que ceux-ci verifient 
-- la contrainte sur le nombre de chiffres

create sequence seq_idm start  10000 increment  1 MAXVALUE 99999;

insert into marque_new 
	select nextval('seq_idm'), Id, 'd'  from marque M, depots_init1 
               D, pays P 
                 where  M.classe = D.classe and  M.nom= D.nom 
                        and M.pays = P.code and P.nom= D.pays; 
--INSERT 0 77

--Depots
-- on cree la sequence des id de depots qui est locale a ce schema


insert into depots 
	select  D.num_dep, P.code, D.Date_dep, M1.IdMarqueNew,G.idg
	from depots_init1 D, marque_new M1, marque M,  tmp_grp G, pays P
	where M.nom = D.nom 
		and M.classe = D.classe
		and M.pays = P.code 
		and P.nom= D.pays
		and G.ndep = D.num_dep
		and G.pays = D.pays
                and M1.IdMarqueOld=M.Id
;
--INSERT 0 77

-- Les groupes ne sont pas mis à jour car un groupe ne devient 
-- propriétaire que lors de l'enregistrement de la marque)
