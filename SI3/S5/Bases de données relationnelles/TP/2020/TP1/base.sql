pays (
code CHAR (2) NOT NULL PRIMARY KEY ,
nom VARCHAR (50) NOT NULL
);

classe (
num INT NOT NULL PRIMARY KEY ,
libelle VARCHAR (30) NOT NULL
);

societe (
id INT NOT NULL PRIMARY KEY ,
nom VARCHAR (40) NOT NULL ,
ville VARCHAR (20) ,
pays CHAR (2) NOT NULL REFERENCES pays
);

marque (
id INT NOT NULL PRIMARY KEY ,
nom VARCHAR (30) NOT NULL ,
classe INT NOT NULL REFERENCES classe ,
pays CHAR (2) NOT NULL REFERENCES pays ,
prop INT NOT NULL REFERENCES societe
);

enr (
marque INT NOT NULL REFERENCES marque ,
num INT NOT NULL ,
pays CHAR (2) NOT NULL REFERENCES pays ,
deposant INT NOT NULL REFERENCES societe ,
date_enr DATE NOT NULL ,
CONSTRAINT cle_enr PRIMARY KEY (num , pays )
);

vente (
marque INT NOT NULL REFERENCES marque ,
vendeur INT NOT NULL REFERENCES societe ,
acquereur INT NOT NULL REFERENCES societe ,
date_vente DATE NOT NULL
);