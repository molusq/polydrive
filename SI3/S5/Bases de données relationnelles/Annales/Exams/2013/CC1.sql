

DROP TABLE  IF EXISTS Employes;
CREATE TABLE Employes
        ( 
       	 nom_employe CHAR(30),
       	 ville CHAR(30) , 
	 rue CHAR(30)
	 );

DROP TABLE  IF EXISTS Entreprises;
CREATE TABLE Entreprises
        ( 
       	 nom_entreprise CHAR(30),
       	 ville CHAR(30) , 
	 rue CHAR(30)
	 );

DROP TABLE  IF EXISTS Travaille;
CREATE TABLE Travaille
        ( 
       	 nom_employe CHAR(30),
       	 nom_entreprise  CHAR(30) , 
	 salaire INT
	 );
INSERT INTO Employes VALUES ('Durand', 'Nice', 'Garibaldi');
INSERT INTO Employes VALUES ('Dumond', 'Nice', 'Jaures');
INSERT INTO Employes VALUES ('Martinez', 'Nice', 'Port');
INSERT INTO Employes VALUES ('Che', 'Cannes', 'Jaures');
INSERT INTO Employes VALUES ('Dupond', 'Cannes', 'Jaures');
INSERT INTO Employes VALUES ('Fide', 'Biot', 'Claire');

INSERT INTO Entreprises VALUES ('Banquissimo', 'Nice', 'Reine Claude');
INSERT INTO Entreprises VALUES ('EspaceRouge', 'La Trinité', 'Orangers');
INSERT INTO Entreprises VALUES ('Banquissimo', 'Cannes', 'Festival');
INSERT INTO Entreprises VALUES ('EspaceRouge', 'La Trinité', 'Libertés');
INSERT INTO Entreprises VALUES ('EspaceVerts', 'Vallauris', 'Des champs');

INSERT INTO Travaille VALUES ('Durand', 'Banquissimo', 4000);
INSERT INTO Travaille VALUES ('Martinez', 'Banquissimo', 4000);
INSERT INTO Travaille VALUES ('Dupond', 'Banquissimo', 3000);
INSERT INTO Travaille VALUES ('Che', 'EspaceRouge', 6000);
INSERT INTO Travaille VALUES ('Fide', 'EspaceRouge', 4500);
INSERT INTO Travaille VALUES ('Dumond', 'EspaceVerts', 6000);

-- Q1:  Affiche les noms de tous les employés qui travaillent pour "Banquissimo"
SELECT nom_employe FROM Travaille T 
       WHERE  T.nom_entreprise = 'Banquissimo';

-- Q2 Affiche  par ordre croissant les noms les villes dans lesquelles réside au moins un des employés qui travaillent pour "Banquissimo".
SELECT  DISTINCT E.ville FROM Employes E 
       WHERE  E.nom_employe IN (SELECT  T.nom_employe FROM Travaille T WHERE  T.nom_entreprise = 'Banquissimo')
       ORDER BY ville;

-- Q3  Affiche les noms des entreprises qui sont localisées dans au moins deux villes différentes.
SELECT DISTINCT E1.nom_entreprise FROM Entreprises E1
       WHERE EXISTS (SELECT * FROM Entreprises E2 WHERE E1.nom_entreprise =E2.nom_entreprise AND E1.ville <> E2.ville);
-- ou
SELECT E.nom_entreprise,COUNT(distinct E.ville) AS NB_Villes FROM Entreprises E
       -- ou SELECT E.nom_entreprise FROM Entreprises E
       GROUP BY E.nom_entreprise
       HAVING COUNT(distinct E.ville) > 1;

-- Q4 Affiche  par ordre alphabétique décroissant le nom de chaque entreprise avec le nombre de ses localisations différentes.
SELECT  E.nom_entreprise, COUNT(*)  FROM Entreprises E
       GROUP BY nom_entreprise
       ORDER BY 1 DESC;


-- Q5 Affiche par ordre alphabétique croissant le nom des entreprises et le nombre de ses d'employés pour les entreprises qui ont plus d'un employé.
 SELECT T.nom_entreprise, COUNT(*) FROM  Travaille T
 	GROUP BY   nom_entreprise
	HAVING COUNT(*) > 1
      	ORDER BY 1;
-- Q6 Affiche par ordre alphabétique croissant le nom des entreprises et le nombre de ses d'employés qui gagnent plus de 3500 pour les entreprises qui ont plus d'un employé qui gagne plus de 3500
 SELECT T.nom_entreprise, COUNT(*) FROM  Travaille T
 	WHERE salaire > 3500
 	GROUP BY   nom_entreprise
	HAVING COUNT(*) > 1
      	ORDER BY 1;