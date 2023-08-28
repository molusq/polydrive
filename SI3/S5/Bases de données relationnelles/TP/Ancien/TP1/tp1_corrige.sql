/* TP1 */

--  \i  tp1.sql

\c tp1

/* 
1. Les marques classees par pays et classe 
*/
select M.nom AS NomMarque, M.pays, M.classe from marque M
order by 3, 2;
-- ou order by M.pays,  M.classe;

/* 
 
    nommarque    | pays | classe
-----------------+------+--------
 Shalimar        | DE   |      1
 Passion         | DE   |      1  
..
(92 rows) */

/*
2. Pour chaque societe, les marques dont elle est proprietaire (ne pas afficher les societe sans marques).
*/

select S.Nom As Societe, M.nom AS NomMarque , S.pays AS NomPays
     from marque M, societe S 
       	     where M.prop=S.id
       	     order by S.id;   
/*
   nommarque    |        nompays         | pays
-----------------+------------------------+------
 Coca Cola       | Coca Cola, Inc.        | US  
..
(92 rows) */

/*
3. Les societes sans marques
*/

select S.nom, S.pays  from societe S
where not exists (select * from marque  M where M.prop=S.id);

-- ou, encore
select S.nom, S.pays  from societe S
where S.id not in (select M.prop from marque  M);

-- ou, encore (mais supprime les homonymes)

select S.nom , S.pays  from societe S 
except 
select S.nom , S.pays  from societe S, marque  M where M.prop=S.id;
/*
               nom                | pays
----------------------------------+------
 SUN MicroSystems, Inc.           | US
 Ste. SUN                         | FR   
REPONSES:
..
(32 rows) */

/*
4. Les marques vendues avant leur enregistrement
*/

select distinct M.nom , M.pays, M.classe  from marque M, enr E, vente V
       where M.id=E.marque and V.marque=M.id and V.date_vente<E.date_enr;
       
-- attention résultat vide si table enreg est vide (alors qu'il faudrait afficher toutes les marques vendues)



select distinct  M.nom , M.pays, M.classe from marque M
       where M.Id in  (select  E.marque  from enr E
       	     	      where E.marque in  (select V.marque from vente V where  V.date_vente<E.date_enr)
		      );
--  résultat aussi vide si table enreg est vide	!	      

select distinct  M.nom , M.pays, M.classe from marque M
       WHERE M.ID in (
       SELECT V0.marque  from vente V0
       EXCEPT
       SELECT V1.marque from vente V1, enr E
       	      WHERE  V1.date_vente > E.date_enr and E.Marque = V1.Marque
       );
--  résultat correct  si table enreg est vide	!

/*
    nom     | pays | classe
------------+------+--------
 Lanvin     | FR   |     11
 Lanvin     | UK   |     11
 Lanvin     | US   |     11
 Orangina   | US   |      4
 PEPSI      | FR   |      4
 Passion    | US   |      1
 Pepsi Cola | FR   |      4
(7 rows) */
    
/*
5. Les marques non enregistrees, classees par pays
*/

select M.nom, M.pays, M.classe from marque M
   except
select M.nom, M.pays, M.classe from marque M, enr E
       where M.id=E.marque order by 2;
       
--  ou encore

select M.nom, M.pays, M.classe from marque M
       where M.id  in
       (select M1.Id  from marque M1
       except
       select E.marque   from enr E)
       ;
--  ou encore

select M.nom, M.pays, M.classe from marque M
       where not exists (select * from enr E where M.id=E.marque )
       order by 2;

--  ou encore

select M.nom, M.pays, M.classe from marque M
       where M.id not in (select E.marque from enr E )
       order by 2;

/* 
      nom      | pays | classe
---------------+------+--------
 Cortisol      | DE   |     10
 ORACLE7       | DE   |      6
 Cola          | FR   |      4
 Cortisol      | FR   |     10
 Dune          | FR   |      4
 ORACLE7       | FR   |      6
 Cortisol      | MX   |     10
 Coup de Coeur | MX   |     11
 Drobsol       | MX   |     10
 Dune          | MX   |      1
 Exorcyl       | MX   |     10
 Miss Dior     | MX   |      1
 Cortislo      | UK   |     10
(13 rows)                        
*/

/*
6. Les proprietaires avec leur nombre de marques
*/

select S.nom AS NomSoc, S.pays, count(*) AS Nb 
from marque M, societe S
where M.prop=S.id 
group by S.Id,  S.nom, S.pays ;

select S.nom AS NomSoc, S.pays,  T.N 
       from societe S, (select M.prop, count(*) AS N from marque M group by  M.prop  ) AS T
where T.prop=S.id 
order by S.Id,  S.nom, S.pays ;
/*
         nomsoc         | pays | nb
------------------------+------+----
 Coca Cola, Inc.        | US   | 10
 Ste. Coca Cola         | FR   |  3
 Coca Cola Gmbh.        | DE   |  5
 ORACLE Corp.           | US   |  5
 Ste. ORACLE            | FR   |  1
 ORACLE Ltd.            | UK   |  1
 ORACLE Gmbh.           | DE   |  1
 Ste.Christian DIOR     | FR   | 10
 Ste. L.V.H.M.          | FR   |  8
 Ste. GUERLAIN          | FR   |  9
 Lab. Roussel           | FR   |  8
 Helena Rubinstein Ltd. | US   |  1
 Hoescht  Gmbh.         | DE   |  7
 PEPSI Inc.             | US   |  7
 Ste. Danone            | FR   |  5
 Ste. Orangina          | FR   |  6
 Pepsi Gmbh.            | DE   |  2
 Soc. Perfumes y Trajes | MX   |  3
(18 rows)              
*/
select S.nom, S.pays, N.NB from societe S,
       (select prop, count(*) as NB from marque group by prop) AS N
       where S.id=N.prop;

/*
7. Les marques homonymes (meme nom et classe) de pays differents et
   de proprietaires differents
*/

select M1.nom AS NOM_M, M1.classe AS CLASSE,  M1.pays AS PAYS_M1,
 M1.prop AS PROP1, M2.pays AS PAYS_M2,  M2.prop AS PROP2 
from marque M1,  marque M2
where  M1.nom =M2.nom and M1.classe=M2.classe and M1.prop<>M2.prop 
       and   M1.pays<M2.pays ;

/*
On notera que pour eliminer les doublons symetriques on a utilise < plutot
que <> pour la derniere condition

      nom_m      | classe | pays_m1 | prop1 | pays_m2 | prop2
-----------------+--------+---------+-------+---------+-------
 Coca Cola       |      4 | DE      |     3 | FR      |     1
 Coca Cola       |      4 | DE      |     3 | UK      |     1
 Coca Cola       |      4 | DE      |     3 | US      |     1
...
(54 rows)     
*/

/*
8. Les proprietaires dont les marques ne sont pas toutes enregistrees
*/
  
select  distinct S.nom, S.pays from  societe S, marque M
	where S.id=M.prop and
	      not exists (select * from enr E  where E.marque=M.id );

select  distinct S.nom, S.pays from  societe S 
	where S.id IN (select M.prop from marque M
	      	      	      where M.id not in
			      	    	 (select E.marque from enr E));

select  distinct S.nom, S.pays from  societe S 
	where S.id IN (select M.prop from marque M
	      	      	      where M.id  <> all
			      	    	 (select E.marque from enr E));
			 

/*
          nom           | pays
------------------------+------
 Hoescht  Gmbh.         | DE
 Lab. Roussel           | FR
 ORACLE Corp.           | US
 Soc. Perfumes y Trajes | MX
 Ste. Coca Cola         | FR
 Ste. Danone            | FR
(6 rows)        
*/



/*
9. Les proprietaires dont toutes les marques sont enregistrees
*/
select   S.nom, S.pays from  societe S
where S.id in (select M.prop from marque M 
               except
	       select M.prop from marque M  
                 where M.id not in (select E.marque from enr E)
		 );
-- ou 
select distinct  S.nom, S.pays from  societe S, marque M
where S.id=M.prop and  not exists (select * from marque MM  where
    S.id=MM.prop   and not exists (select * from enr E  where E.marque=MM.id ));
-- ou 
select  S.nom, S.pays from  societe S, marque M where S.id=M.prop  
except
select  S.nom, S.pays from  societe S, marque M
where  S.id=M.prop  and not exists (select * from enr E  where E.marque=M.id );

/* 
          nom           | pays
------------------------+------
 Coca Cola Gmbh.        | DE
 Coca Cola, Inc.        | US
 Helena Rubinstein Ltd. | US
 ORACLE Gmbh.           | DE
 ORACLE Ltd.            | UK
 PEPSI Inc.             | US
 Pepsi Gmbh.            | DE
 Ste. GUERLAIN          | FR
 Ste. L.V.H.M.          | FR
 Ste. ORACLE            | FR
 Ste. Orangina          | FR
 Ste.Christian DIOR     | FR
(12 rows)                     
*/


/*
10. Pour chaque classe, le nom et pays de la (des) societe(s) qui possede le
plus de marques enregistrees dans cette classe
*/

-- Requete avec utilisation des vues et de max 
DROP VIEW IF EXISTS NBM_proprio;
CREATE view NBM_proprio AS
(select M.classe, M.prop,  count(*) AS NB
 from  marque M
 group by M.classe, M.prop ); 

select classe, prop, NB 
       from NBM_proprio V1 
       where NB = (select Max(NB) from NBM_proprio V2
       where V1.classe=V2.classe);

select V.classe,V.prop, Max(V.Nb)
       FROM NBM_proprio
       GROUP BY V.classe,V.prop;
       		 	   

Select distinct NBM1.classe, NBM1.NB, S.nom, S.pays
 from  NBM_proprio AS NBM1, societe S
 where S.Id=NBM1.prop and  
 NBM1.NB= (select  max(NBM2.NB)
           from  NBM_proprio AS NBM2 where NBM2.classe=NBM1.classe);
-- Requete 10 avec utilisation de la clause having
select M1.classe, S.nom, S.pays, count( * )
  from marque as M1, societe AS S
  where M1.prop = S.id
  group by M1.classe, S.nom, S.pays
  having count( * )  >= all
        (select count ( * ) from marque AS M2
        where M2.classe = M1.classe
        group by M2.prop);

with  NBM_proprio AS
(select M.classe, M.prop,  count(*) AS NB
 from  marque M
 group by M.classe, M.prop )

Select distinct T.classe, T.nom, T.pays from (
Select distinct NBM1.classe, NBM1.NB, S.nom, S.pays, rank( ) over
( partition by classe  order by nb desc) as classement
 from  NBM_proprio AS NBM1, societe S
 where S.Id=NBM1.prop ) as T where T.classement =1
 ;
 


/* 
 classe | COMPTE MAXI |    NOM SOC.     | PAYS SOC.
--------+-------------+-----------------+-----------
      1 |           8 | Ste. GUERLAIN   | FR
      4 |          10 | Coca Cola, Inc. | US
      6 |           5 | ORACLE Corp.    | US
     10 |           8 | Lab. Roussel    | FR
     11 |           8 | Ste. L.V.H.M.   | FR               
(5 rows)   
*/

Select distinct T.classe, T.nom, T.pays from (
Select distinct NBM1.classe, NBM1.NB, S.nom, S.pays, rank( ) over
( partition by classe  order by nb desc) as classement
 from  NBM_proprio AS NBM1, societe S
 where S.Id=NBM1.prop ) as T where T.classement =1
 ;
 
with  NBM_proprio AS
(select M.classe, M.prop,  count(*) AS NB
 from  marque M
 group by M.classe, M.prop )


Select distinct NBM1.classe, NBM1.NB, S.nom, S.pays
 from  NBM_proprio AS NBM1, societe S
 where S.Id=NBM1.prop and
 NBM1.NB= (select  max(NBM2.NB)
           from  NBM_proprio AS NBM2 where NBM2.classe=NBM1.classe);

/* Contraintes */


/*
1. Le pays d'une marque est le meme que le pays ou elle est enregistree
*/

 
Select  M.nom, M.classe, M.pays AS PaysMarque, E.pays As PaysEnr 
	from  marque M, enr E
     	where  M.id=E.marque and E.pays<>M.pays;

/*
   nom    | classe | PaysMarque | PaysEnr 
----------+--------+------------+------
 Cortisol |     10 | US         | MX
(1 ligne)
*/


/*
2. Le vendeur d'une marque est soit le deposant, soit l'acquereur
de la vente precedente

Il faut essayer de trouver un vendeur d'une marque qui viole cette contrainte :
   - soit parce c'est la premiere vente de cette marque et qu'il n'en est pas
   le deposant 
   - soit parce qu'il n'est pas l'acquereur de la vente precedente

La difficulte est de trouver la vente precedente !!!!!
*/

-- Le premier vendeur n'est pas le deposant
Select V.vendeur, V.marque, V.date_vente  from vente V
where   not exists (select * from vente V1 
	where V1.marque=V.marque and V1.date_vente<V.date_vente)
        and  not exists (select * from enr E 
		      where  E.deposant=V.vendeur and E.marque=V.marque)
union all -- pour ne pas supprimer les doublons
-- Le vendeur n'est pas l'acquereur de la vente precedante
select V1.vendeur, V1.marque, V1.date_vente  from vente V1, vente V2
where  V1.marque=V2.marque   and V1.date_vente<V2.date_vente 
                           and V1.acquereur <> V2.vendeur
       and not exists (select * from vente V3   -- la marque n'a pas ete vendu entre V1 et V2
		       where   V3.marque=V2.marque and 
			       V1.date_vente< V3.date_vente and 
			       V3.date_vente< V2.date_vente );


-- ou encore

select V.vendeur, V.marque, V.date_vente  from vente V
where
       V.marque != all (select V1.marque from vente V1  -- Il n'y a pas eu de vente avant V
		        where  V1.date_vente<V.date_vente)
       and 
       V.vendeur != all (select E.deposant from enr E   -- Le vendeur n'est pas le deposant
		         where  E.marque=V.marque)
union all 
select V.vendeur, V.marque, V.date_vente  from vente V, vente V1
where      V1.marque=V.marque    
       and V1.date_vente<V.date_vente 
       and V1.acquereur <> V.vendeur
       and V.marque != all (select V2.marque from vente V2 
		            where   V1.date_vente< V2.date_vente and 
			            V2.date_vente< V.date_vente );

/*
vendeur | marque | date_vente 
---------+--------+------------
      12 |     30 | 2068-03-07
       5 |      3 | 1980-02-05
*/

/*
3. Le pays d'une marque est le meme que celui de son proprietaire
*/

select distinct M.nom, M.classe, M.pays, S.pays from  marque M, societe S
where  M.prop=S.id  and S.pays =M.pays;
/*
       nom       | classe | pays | pays
-----------------+--------+------+------
 Coca Cola       |      4 | FR   | US
 Coca Cola Light |      4 | FR   | US
...
(48 rows)      
*/
