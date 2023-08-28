
DROP table client;
CREATE TABLE client 
( CLI_ID   int,
 CLI_NOM   char(12));
INSERT INTO Client VALUES (1 , 'Dupont');
INSERT INTO Client VALUES ( 2);
INSERT INTO Client VALUES (3 , 'Durand');


DROP table telephone;
CREATE TABLE telephone
( CLI_ID   int,
TEL char(18));
INSERT INTO telephone VALUES (1,'05-59-45-72-42' );
INSERT INTO telephone VALUES (3,'01-44-28-52-50' );
INSERT INTO telephone VALUES (3,'06-54-18-51-90' );


DROP table email;
CREATE TABLE  email
( CLI_ID   int,
 EML_ADRESSE  char(14));
INSERT INTO email VALUES (1,'dupe@free.fr' );
INSERT INTO email VALUES (1,'dd@hotmail.com' );
INSERT INTO email VALUES (2,'mm@free.fr' );

DROP table adresse;
CREATE TABLE   adresse
( CLI_ID   int,
 ADR_VILLE CHAR(20));
INSERT INTO adresse VALUES (2,'Nice' );
INSERT INTO adresse VALUES (2,'Paris' );
INSERT INTO adresse VALUES (4,'Pau' );

/*
On veux contacter tous les clients, quelque soit le mode de contact, dans le  cadre d'une campagne publicitaire. 
Une reponse contenant tous les clients, meme ceux qui n'ont pas de telephone, d'e-mail ou d'adresse est donc souhaitee.
*/


SELECT *
FROM Client 
   FULL OUTER JOIN telephone 
        USING(CLI_ID) 
   FULL OUTER JOIN email  
        USING(CLI_ID) 
   FULL OUTER JOIN adresse  
        USING(CLI_ID) ;

/*
 
 cli_id |   cli_nom    |        tel         |  eml_adresse   |      adr_ville       
--------+--------------+--------------------+----------------+----------------------
      1 | Dupont       | 05-59-45-72-42     | dupe@free.fr   | 
      1 | Dupont       | 05-59-45-72-42     | dd@hotmail.com | 
      2 |              |                    | mm@free.fr     | Nice                
      2 |              |                    | mm@free.fr     | Paris               
      3 | Durand       | 01-44-28-52-50     |                | 
      3 | Durand       | 06-54-18-51-90     |                | 
      4 |              |                    |                | Pau                 
(7 rows)
*/



 SELECT cli_id, cli_nom, tel, eml_adresse, adr_ville 
   FROM Client 
   LEFT OUTER  JOIN telephone  
        USING(CLI_ID) 
   LEFT OUTER JOIN email  
        USING(CLI_ID) 
   LEFT  OUTER JOIN adresse  
        USING(CLI_ID) ;

/*
cli_id |   cli_nom    |        tel         |  eml_adresse   |      adr_ville       
--------+--------------+--------------------+----------------+----------------------
      1 | Dupont       | 05-59-45-72-42     | dupe@free.fr   | 
      1 | Dupont       | 05-59-45-72-42     | dd@hotmail.com | 
      2 |              |                    | mm@free.fr     | Nice                
      2 |              |                    | mm@free.fr     | Paris               
      3 | Durand       | 01-44-28-52-50     |                | 
      3 | Durand       | 06-54-18-51-90     |                | 
(6 rows)
*/

