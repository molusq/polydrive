
-- TP2: Phase 3



DROP  VIEW IF EXISTS cstc;
DROP  VIEW IF EXISTS cstb;
DROP  VIEW IF EXISTS csta;
DROP TABLE IF EXISTS depots_incor;
DROP TABLE IF EXISTS depots_init1 CASCADE;
DROP TABLE IF EXISTS depots_init;
DROP TABLE IF EXISTS deposants_init1;
DROP TABLE IF EXISTS deposants_init;



-- Etape 1

-- Copie des tables depots_init et deposants_init


CREATE TABLE depots_init (
	nom  VARCHAR(30)  NOT NULL,
	classe INT  NOT NULL,
	pays VARCHAR(50),
	num_dep INT NOT NULL,
	date_dep DATE  NOT NULL                         
	);

\copy  depots_init from 'depots_init'

ALTER TABLE depots_init add idd SERIAL;
/*
-- Ou :Creation d'une copie de travail de la table des depots initiaux 
-- avec une clé

CREATE TABLE depots_init1 (
	idd SERIAL,
	nom  VARCHAR(30)  NOT NULL,
	classe INT  NOT NULL,
	pays VARCHAR(50),
	num_dep INT NOT NULL,
	date_dep DATE  NOT NULL                         
	);
INSERT into   depots_init1 (nom, classe, pays, num_dep, date_dep) 
	SELECT  nom, classe, pays, num_dep, date_dep  from  depots_init;
/* ou
 \copy  depots_init(nom,classe,pays,num_dep,date_dep) from '../Tables/depots_init'

*/
CREATE TABLE deposants_init (
	num_dep INT   NOT NULL,
	pays VARCHAR(50)  NOT NULL,
	deposant INT  NOT NULL                         
	);

\copy  deposants_init from 'deposants_init'


--Creation d'une copie de travail de la table des deposants initiaux
-- avec une clé


CREATE TABLE deposants_init1 (	
	idd SERIAL,
	num_dep INT   NOT NULL,
	pays VARCHAR(50)  NOT NULL,
	deposant INT  NOT NULL                         
	);
INSERT into   deposants_init1(num_dep,pays,deposant) 
	select num_dep,pays,deposant from deposants_init;


-- Etape 2 : vérification des contraintes a, b, c et d

-- Les contraintes a et d peuvent être vérifiées à l'aide d'une 
-- référence si les tables concernées ont une clé primaire

-- a) Deux depots dans un meme pays avec le meme numero doivent concerner
--    la meme marque

select P1.idd, P1.nom, P2.idd, P2.nom
       from  depots_init1 P1, depots_init1 P2
       where P1.pays=P2.pays
       	     and P1.num_dep=P2.num_dep
	     and (P1.nom<P2.nom
	     	 or P1.pays<P2.pays
		 or P1.classe <P2.classe);
-- idd |    nom    | idd |    nom    
-------+-----------+-----+-----------
--  80 | COCA COLA |  59 | Coca Cola



-- b) dans un meme pays, les numeros de depot respectent l'ordre
--    chronologique 

select P1.idd,  P1.date_dep, P2.idd, P2.date_dep 
	from depots_init1 P1,  depots_init1 P2
  		where P1.pays=P2.pays and P1.num_dep<P2.num_dep 
                      and P1.date_dep>P2.date_dep; 
-- idd |  date_dep  | idd |  date_dep
--  35 | 1990-05-01 |  81 | 1990-04-01 (35 : Dune, 81 : Poison )


-- c) la classe figurant dans un depot doit exister dans la table
--    classe 

select * from depots_init1 P where not exists
  (select * from classe C where P.classe=C.num);


-- idd |  nom   | classe |  pays   | num_dep |  date_dep
--  82 | Passat |     37 | Germany |  715400 | 1991-05-01


-- d) le pays figurant dans un depot doit exister dans la table
--    pays 


select D.num_dep, D.pays from depots_init D where D.pays not in 
  (select P.nom  from pays P );
-- num_dep | pays
-- (0 rows)


-- 
-- Etape 3 : pré-traitement des données
--

-- creation de la table des depots incorrects

create table depots_incor
(      noer     INTEGER, -- numéro de la contrainte violée
       idd 	INTEGER,
       nom VARCHAR(30)	not null,
       classe 	INTEGER not null,
       pays 	VARCHAR(50) not null, 
       num_dep 	INTEGER	not null,
       date_dep	DATE not null,
constraint cle_dinc primary key(idd,noer)
);

-- insertion des tuples qui violent les contraints a, b et c dans la 
-- table des depots incorrects à partir des vues associées à ces contraintes

-- contrainte a: il s'agit du 59 et du 80 

create VIEW  csta AS (
       select 1, D1.idd, D1.nom, D1.classe, D1.pays, D1.num_dep, D1.date_dep		from  depots_init D1,  depots_init D2
       	    where D1.pays=D2.pays
	    	  and D1.num_dep=D2.num_dep
		  and D1.nom<>D2.nom
		      ) ;


--  contrainte b: il s'agit des tuples 35 et 81
 

create VIEW  cstb AS (
       select 2, D1.idd, D1.nom, D1.classe, D1.pays, 
       D1.num_dep, D1.date_dep  
       from depots_init D1, depots_init D2 
       where D1.pays=D2.pays and D1.num_dep<D2.num_dep 
       and D1.date_dep>D2.date_dep
           UNION ALL
       select 2, D2.idd, D2.nom, D2.classe, D2.pays, D2.num_dep, 
       D2.date_dep   from depots_init D1, depots_init D2 
       where D1.pays=D2.pays and D1.num_dep<D2.num_dep 
       and D1.date_dep>D2.date_dep
);

-- contrainte c: il s'agit du tuples 82


create VIEW  cstc  AS (select 3,
  D.idd, D.nom, D.classe, D.pays, D.num_dep, D.date_dep 
  from depots_init D where not exists
      (select * from classe C where D.classe=C.num));

create VIEW  cstd  AS (select 4,D.num_dep, D.pays from depots_init D where D.pays not in 
  (select P.nom  from pays P ));


-- insertion dans la table des dépots incorrects
insert into depots_incor  (
       select * from  csta
       union  select * from cstb
       union  select * from cstc
       union  select * from cstd); 
\copy depots_incor to 'depots_incor.csv'

-- INSERT 0 5

-- retrait de la table depots_init des tuples invalides
delete from depots_init
       where idd in (select idd from depots_incor);
-- DELETE 5

