DROP VIEW  IF EXISTS employes;
DROP TABLE  IF EXISTS Depot ;
DROP TABLE  IF EXISTS Brevet ;
DROP TABLE  IF EXISTS  Personne ;
DROP TABLE  IF EXISTS Organisme ;
DROP TABLE  IF EXISTS Pays ;

CREATE TABLE Pays 
        (
         IdPays CHAR(2) PRIMARY KEY ,
         Nom VARCHAR(30)
         );

CREATE TABLE Organisme
        (
         IdOrganisme INT PRIMARY KEY, 
         NomOrganisme VARCHAR(30), 
         NbEmployes INT, 
         IdPays CHAR(2)  REFERENCES Pays
        ); 

CREATE TABLE Personne 
        ( 
         IDPersonne INT PRIMARY KEY,
       	 Nom VARCHAR(30),
       	 Prenom VARCHAR(30) , 
	 Age INT, 
         IdPaysNaissance CHAR(2)  REFERENCES Pays,
         IdOrganisme  INT REFERENCES Organisme
	 );

CREATE TABLE Brevet 
        ( 
       	 NumB INT PRIMARY KEY  CHECK (NumB >= 1000),
       	 IdOrganisme INT REFERENCES Organisme,
         IdPersonne INT REFERENCES Personne
        );

CREATE TABLE Depot 
        (
        NumB INT REFERENCES Brevet, 
        DateDpt DATE CHECK (DateDpt >= '1789-07-13'), 
        IdPays CHAR(2)  REFERENCES Pays,
        PRIMARY KEY (NumB,DateDpt,IdPays)
        );

INSERT INTO Pays  VALUES ('FR', 'France');
INSERT INTO Pays  VALUES ('DE', 'Germany');
INSERT INTO Pays  VALUES ('BE', 'Belgique');
INSERT INTO Pays  VALUES ('CH', 'Suisse');


INSERT INTO Organisme  VALUES (1,'CNRS',200, 'FR');
INSERT INTO Organisme  VALUES (2,'GMD',500, 'DE');
INSERT INTO Organisme  VALUES (3,'HMAG',2, 'CH');

INSERT INTO Personne VALUES (10, 'Durand','Paul', 25,'FR',1);
INSERT INTO Personne VALUES (20, 'Schmidt','Eva', 39,'DE',2);
INSERT INTO Personne VALUES (30, 'Happart','Noemie', 65,'BE',2);

INSERT INTO Brevet VALUES (1000, 1,10);
INSERT INTO Brevet VALUES (1500, 1,10);
INSERT INTO Brevet VALUES (2000, 2,20);
INSERT INTO Brevet VALUES (2500, 2,30);
INSERT INTO Brevet VALUES (3000, 3,30);

INSERT INTO Depot VALUES (1000, '1789-07-13','FR');
INSERT INTO Depot VALUES (1500, '1789-07-13','FR');
INSERT INTO Depot VALUES (1000, '1799-07-13','CH');
INSERT INTO Depot VALUES (1000, '1899-09-13','BE');
INSERT INTO Depot VALUES (1000, '1999-07-13','DE');
INSERT INTO Depot VALUES (2000, '1999-12-31','FR');
INSERT INTO Depot VALUES (2500, '2001-12-31','FR');
INSERT INTO Depot VALUES (3000, '2011-11-11','FR');

-- Q1:  Noms des organismes de plus de 100 personnes ayant depose un brevet avant l'an 2000
SELECT  O.NomOrganisme  FROM Organisme O 
       WHERE  O.NbEmployes >= 100 AND O.IdOrganisme IN 
              ( SELECT B.IdOrganisme FROM Brevet B WHERE B.NumB IN
                   (SELECT D.NumB FROM Depot D WHERE D.DateDpt < '2000-01-01')
               );  

-- Q2 Afficher l'IdOrganisme des organismes qui ont depose au moins un brevet dans chaque pays

   
 SELECT  B.IdOrganisme  FROM   Brevet B
        EXCEPT                  
 SELECT  T.IdOrganisme  FROM 
     (
      SELECT O.IdOrganisme, P.IdPays   FROM Organisme O, Pays P 
           EXCEPT
      SELECT B.IdOrganisme, D.IdPays   FROM Brevet B, Depot D
             WHERE B.NumB = D.NumB 
     ) AS T;

-- ou
SELECT  O.IdOrganisme  FROM Organisme O 
   WHERE NOT EXISTS 
         (SELECT * FROM Pays P WHERE  P.IdPays NOT IN 
                (SELECT D.IdPays FROM Depot D WHERE D.NumB IN 
                        (SELECT B.NumB FROM Brevet B WHERE B.IdOrganisme =  O.IdOrganisme )
                 )
          );
  
-- Q3  Ecrire une requete SQL qui calcule par IdOrganisme  le nombre de brevets deposes par chaque personne

SELECT B.IdOrganisme, B.IdPersonne, COUNT(*) FROM  Brevet B, Depot D
        WHERE  B.NumB = D.NumB 
        GROUP BY B.IdOrganisme, B.IdPersonne
        ORDER BY  B.IdOrganisme, B.IdPersonne ;

-- Q4  Ecrire une requete SQL qui calcule le nombre total des employes qui travaillent dans les organismes qui ont au moins deposes 2 brevets
CREATE  OR REPLACE VIEW Employes AS
  (SELECT  O.IdOrganisme, COUNT(*)  FROM Organisme O, Brevet B, Depot D
        WHERE O.IdOrganisme = B.IdOrganisme AND B.NumB = D.NumB 
        GROUP BY O.IdOrganisme 
        HAVING COUNT(*) >=2);
-- ou
CREATE  OR REPLACE VIEW Employes AS
  (SELECT  B.IdOrganisme FROM  Brevet B
        GROUP BY B.IdOrganisme 
        HAVING COUNT(*) >=2);

SELECT SUM(O.NbEmployes) FROM Organisme O
       WHERE O.IdOrganisme IN (SELECT  E.IdOrganisme FROM Employes E);
       