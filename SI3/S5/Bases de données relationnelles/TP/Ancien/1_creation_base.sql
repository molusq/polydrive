

-- TP2 :  Creation d'un schema pour le traitement des marques deposees ---



-- \c marquesdeposees;


-- les tables vont être crees dans le schema de l'utilisateur courant


-- Suppression des tables existantes dans l'ordre inverse de leur creation

DROP TABLE IF EXISTS ventes ;
DROP TABLE IF EXISTS enregistrements  ;
DROP TABLE IF EXISTS refus  ;
DROP TABLE IF EXISTS depots  ;
DROP TABLE IF EXISTS marque_new  ;
DROP TABLE IF EXISTS groupes_societes  ;
DROP TABLE IF EXISTS statuts  ;
DROP TABLE IF EXISTS groupes  ;






-- Creation des tables pour le traitement des marques deposees 


CREATE TABLE groupes (
       IdGroupe INT  PRIMARY KEY,
       Nom VARCHAR(50));

CREATE TABLE groupes_societes (
       IdGroupe INT  NOT NULL REFERENCES groupes,
       IdSociete INT  NOT NULL REFERENCES societe,
       primary key (IdGroupe, IdSociete ));

CREATE TABLE statuts (	-- une autre manière de definir un type enumere
       Id char  PRIMARY KEY,
       Nom varchar(20) NOT NULL);
insert into statuts values ('d', 'deposee');
insert into statuts values ('n', 'nouvelle');
insert into statuts values ('l', 'libre');
insert into statuts values ('e', 'enregistree');
 
   
Create TABLE marque_new (
       IdMarqueNew INT  PRIMARY KEY CHECK (IdMarqueNew>=1000 and IdMarqueNew <100000),
       IdMarqueOld INT REFERENCES marque,  
               -- Autre solution: creer une table marque_new sans reference a l'ancienne 
               -- et recopier toutes les tuples de l'ancienne
       Statut char DEFAULT 'n' NOT NULL REFERENCES  statuts,
       IdGroupe INT  REFERENCES groupes); 
                      -- Pas d'option not null pour permettre utilisation dans depots
       -- CONSTRAINT Cle_Sec1 UNIQUE (Nom,Classe,Pays)): doit être defini sur marque
       


CREATE TABLE depots (
       NumLegal INT NOT NULL,
       Pays CHAR(2) NOT NULL REFERENCES pays,
		PRIMARY KEY (NumLegal,Pays),
       DateDepot DATE NOT NULL,
       IdMarque INT  NOT NULL REFERENCES marque_new,
       IdGroupe INT  NOT NULL REFERENCES groupes
);
      

CREATE TABLE refus (
      	NumDepot INT NOT NULL,
       	PaysDepot char(2) NOT NULL REFERENCES pays,
		FOREIGN KEY (NumDepot,PaysDepot) REFERENCES Depots,
      	 DateRefus date NOT NULL)
;
-- La table refus est necessaire si on veut memoriser la date de refus


CREATE TABLE enregistrements (
       NumLegal INT NOT NULL,
       Pays char(2) NOT NULL REFERENCES pays,
		PRIMARY KEY (NumLegal,Pays),
       DateEnreg date NOT NULL,
       	NumDepot INT NOT NULL,
       	PaysDepot char(2) NOT NULL REFERENCES pays,
		FOREIGN KEY (NumDepot,PaysDepot) REFERENCES Depots
);

/*
Contrainte textuelle : 
Une marque enregistree doit forcement avoir ete deposee: dans cette table, 
un enregistrement fait reference a un numero de depot et par consequent 
la contrainte est toujours verifiee 
*/

CREATE TABLE ventes (
        IdVente SERIAL PRIMARY KEY,   
                -- Ajoute pour facilite la reference a la vente precedente
       	NumLegal INT NOT NULL,
       	PaysVente char(2) NOT NULL REFERENCES pays,
		UNIQUE (NumLegal,PaysVente),
       	DateVente date NOT NULL,
       	IdGroupe INT  NOT NULL REFERENCES groupes,
       	IdEnreg INT  NOT NULL,
       	PaysEnreg  char(2) NOT NULL REFERENCES pays,
		FOREIGN KEY (IdEnreg,PaysEnreg)   REFERENCES  enregistrements,
	VentePrec INT REFERENCES ventes) ;
/*
 Contrainte textuelle : 
Une marque ne peut etre vendue que si elle est enregistree.
Dans notre table, une vente fait reference a un numero d'enregistrement et
par consequent la contrainte est toujours verifiee
*/

