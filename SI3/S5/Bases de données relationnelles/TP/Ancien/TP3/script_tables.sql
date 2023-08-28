/*

Creation de tp1 et de l'ensemble des tables par:
\i script_tables.sql

*/
CREATE DATABASE tp3;
\c tp3

DROP TABLE IF EXISTS  pays cascade;
CREATE TABLE pays (
	code  CHAR(2) NOT NULL PRIMARY KEY,
	nom VARCHAR(50) NOT NULL
	);
\copy pays from 'pays'


DROP TABLE IF EXISTS  classe cascade;
CREATE TABLE classe (
	num  INT  NOT NULL PRIMARY KEY,
	libelle VARCHAR(30)  NOT NULL
	);

\copy classe from 'classe'


DROP TABLE IF EXISTS  societe cascade;
CREATE TABLE societe (
	id  INT  NOT NULL PRIMARY KEY,
	nom VARCHAR(40)  NOT NULL,
	ville VARCHAR(20),
	pays CHAR(2)  NOT NULL REFERENCES pays
	);

\copy societe from 'societe'


DROP TABLE IF EXISTS  marque cascade;
CREATE TABLE marque (
	id  INT NOT NULL  PRIMARY KEY,
	nom VARCHAR(30)  NOT NULL,
	classe INT  NOT NULL REFERENCES classe,
	pays CHAR(2)  NOT NULL REFERENCES pays,
	prop INT  NOT NULL REFERENCES societe
	);

\copy  marque from 'marque'



DROP TABLE IF EXISTS  enr cascade;
CREATE TABLE enr (
	marque  INT  NOT NULL REFERENCES marque,
	num INT  NOT NULL, 
	pays CHAR(2)  NOT NULL REFERENCES pays,
	deposant INT  NOT NULL REFERENCES societe,
	date_enr DATE NOT NULL,
	CONSTRAINT cle_enr PRIMARY KEY (num,pays)
	);

\copy enr from 'enr'


DROP TABLE IF EXISTS  vente cascade;
CREATE TABLE vente (
	marque  INT  NOT NULL REFERENCES marque,
	vendeur INT  NOT NULL REFERENCES societe,	
	acquereur INT   NOT NULL REFERENCES societe,
	date_vente DATE  NOT NULL                         
	);

\copy vente from 'vente'




grant select on table classe to public;
grant select on table enr to public;
grant select on table marque to public;
grant select on table pays to public;
grant select on table societe to public;
grant select on table vente to public;
