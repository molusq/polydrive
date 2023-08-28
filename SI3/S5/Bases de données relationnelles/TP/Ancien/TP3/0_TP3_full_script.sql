
-- Suppression du schéma
drop database if exists marquesdeposees;
drop database if exists test1 ;
-- Creation user test1
drop schema  if exists test1 CASCADE;
drop owned by test1 CASCADE;
drop user  if exists test1;
create user test1 inherit ;
create database test1 with owner test1;
alter user test1 with password'test1';

\c test1

-- Creation BD marquesdeposees qui va contenir le schéma données et les schémas utilisateurs

create database marquesdeposees ; -- elle va contenir un schema par utilisateur
\c marquesdeposees

-- Creation schema donnees

create schema donnees; -- dans le quel on va mettre les donnees partagees
set search_path to donnees, public ;



-- cree et rend visibles au public les tables du schema donnees


CREATE TABLE classe (
	num  INT  NOT NULL PRIMARY KEY,
	libelle VARCHAR(30)  NOT NULL
	);
	
\copy classe from 'classe'

CREATE TABLE pays (
	code  CHAR(2) NOT NULL PRIMARY KEY,
	nom  VARCHAR(50)
	);
	
\copy pays from 'pays'


CREATE TABLE societe (
	id  INT  NOT NULL PRIMARY KEY,
	nom VARCHAR(40)  NOT NULL,
	ville VARCHAR(20),
	pays CHAR(2)  NOT NULL REFERENCES pays
	);
	
\copy societe from 'societe'


CREATE TABLE marque (
        id  INT NOT NULL  PRIMARY KEY,
        nom VARCHAR(30)  NOT NULL,
        classe INT  NOT NULL REFERENCES classe,
        pays CHAR(2)  NOT NULL REFERENCES pays,
        prop INT  NOT NULL REFERENCES societe
        );

\copy marque from 'marque'


CREATE TABLE depot (
        nom VARCHAR(30)  NOT NULL,
        classe INT  NOT NULL REFERENCES classe,
        pays CHAR(2)  NOT NULL REFERENCES pays,
        id  INT  NOT NULL PRIMARY KEY,
	date_depot DATE NOT NULL
        );

\copy depot from 'depots_init'
--grant usage  on  schema donnees to etudiant, public;
grant usage  on  schema donnees to  public;

-- schéma test1
drop  schema if exists test1 ;
create schema authorization test1 ;
alter user test1 set search_path to test1 , donnees, public ;

-- droits 
grant select  ON ALL TABLES IN SCHEMA donnees to test1, public;	
grant references  ON ALL TABLES IN SCHEMA donnees to test1, public;

\c test1

\c marquesdeposees
-- You are now connected to database "marquesdeposees" as user "test1"
/*cd
marquesdeposees=> \d
         List of relations
 Schema  |  Name   | Type  | Owner  
---------+---------+-------+--------
 donnees | classe  | table | rueher
 donnees | marque  | table | rueher
 donnees | pays    | table | rueher
 donnees | societe | table | rueher
(4 rows)

marquesdeposees=> 
*/

\i 1_base.sql
--\i 2_contraintes.sql
--\i 3_verif_donnees.sql
/*
 idd |    nom    | idd |    nom    
-----+-----------+-----+-----------
  80 | COCA COLA |  59 | Coca Cola
(1 row)

 idd |  date_dep  | idd |  date_dep  
-----+------------+-----+------------
  35 | 1990-05-01 |  81 | 1990-04-01
(1 row)

 idd |  nom   | classe |  pays   | num_dep |  date_dep  
-----+--------+--------+---------+---------+------------
  82 | Passat |     37 | Germany |  715400 | 1991-05-01
(1 row)

 num_dep | pays 
---------+------
(0 rows)
*/



--\i '4_trigger_checkpaysdepot.sql'
-- -- trigger qui verifie l'assertion: le pays du depot est celui de la marque
-- CREATE FUNCTION
-- CREATE TRIGGER

--\i 5_maj_tables.sql
-- psql:4_maj_tables.sql:87: ERROR:  pays du depot incorrect pour le depot 11000 dans le pays DE
