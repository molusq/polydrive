DROP DATABASE IF EXISTS tp4 CASCADE;
CREATE DATABASE tp4;

\c tp4;

create schema donnees; -- dans le quel on va mettre les donnees partagees
set search_path to donnees, public ;

/*
1 cursus peut correspondre à plusieurs grp
1 groupe peut correspondre à plusieurs cursus
*/

DROP TABLE IF EXISTS module CASCADE;
CREATE TABLE module(
	code_module varchar(20) PRIMARY KEY,
	libelle varchar(50)
	);

DROP TABLE IF EXISTS groupe CASCADE;
CREATE TABLE groupe(
	groupe varchar(10) PRIMARY KEY);

DROP TABLE IF EXISTS cursus CASCADE;
CREATE TABLE cursus(
	cursus varchar(10) PRIMARY KEY);

DROP TABLE IF EXISTS groupe_cursus CASCADE;
CREATE TABLE groupe_cursus(
	groupe varchar(10) REFERENCES groupe,
	cursus varchar(10) REFERENCES cursus,
	PRIMARY KEY(groupe, cursus)
	);

DROP TABLE IF EXISTS creneaux CASCADE;
CREATE TABLE creneaux(
	code_module varchar(20) REFERENCES module,
	groupe varchar(10) REFERENCES groupe,
	duree int,
	PRIMARY KEY(code_module, groupe)
	);

DROP TABLE IF EXISTS cren CASCADE;
CREATE TABLE csvcreneaux(
	code_module varchar(20),
	libelle varchar(50),
	cursus varchar(10),
	groupe varchar(10),
	duree int);

\copy csvcreneaux from 'creneaux.csv' delimiter ';' csv

DROP TABLE IF EXISTS ed CASCADE;
CREATE TABLE edt(
	code_module varchar(20) REFERENCES module,
	groupe varchar(10) REFERENCES groupe,
	semaine numeric(2,0) CHECK (semaine >= 1 AND semaine <= 52),
	jour numeric(1,0) CHECK (jour >= 1 AND jour <= 5),
	heured numeric(1,0) CHECK (heured >= 1 AND heured <= 8),
	salle varchar(10),
	PRIMARY KEY(code_module, groupe, semaine),
	UNIQUE (semaine, jour, heured, salle),
	FOREIGN KEY (code_module, groupe) REFERENCES creneau
	);

\copy edt from 'edt_49.csv'  delimiter ';' csv

INSERT INTO module (code_module, libelle)
SELECT code_module, libelle
FROM csvcreneaux
GROUP BY code_module, libelle;

INSERT INTO groupe (groupe)
SELECT groupe
FROM csvcreneaux
GROUP BY groupe;

INSERT INTO cursus (cursus)
SELECT cursus
FROM csvcreneaux
GROUP BY cursus;

INSERT INTO groupe_cursus (groupe, cursus)
SELECT groupe, cursus
FROM csvcreneaux
GROUP BY groupe, cursus;

INSERT INTO creneaux (code_module, groupe, duree)
SELECT code_module, groupe, duree
FROM csvcreneaux
GROUP BY code_module, groupe, duree;

/*INSERT INTO edt (code_module, groupe, semaine, jour, heured, salle)
SELECT code_module, groupe, semaine, jour, heured, salle
FROM csvedt
GROUP BY code_module, groupe, semaine, jour, heured, salle;*/
