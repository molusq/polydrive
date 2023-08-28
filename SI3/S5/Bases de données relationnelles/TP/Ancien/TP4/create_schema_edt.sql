


-- Suppresion des tables

Drop table IF EXISTS  edt_sem, cursus_groupe, cursus, groupe, creneau_type, 
        module, edt_init, creneau_init CASCADE; 


-------------------------------------
-- Tables de donnees
-------------------------------------

create table creneau_init(
    code_module  varchar(20),
    libelle varchar(50),
    cursus  varchar(10),
    groupe   varchar(10),
    duree   numeric(1)
);

create table edt_init (
    module  varchar(20),
    groupe   varchar(10),    
    sem     numeric(2),
    jour    numeric(1),
    heured  numeric(1),
    salle   varchar(10)
);

-------------------------------------
-- Table pour le stockage de EDT
-------------------------------------

create table cursus (
    nomcursus   varchar(10) primary key,
    libelle varchar(50)
);
create table groupe (
    nomcursus   varchar(10) primary key,
    libelle varchar(50)
);
create table cursus_groupe(           -- lien entre cursus et groupe
    nomcursus  varchar(10) references cursus,
    groupe   varchar(10) references groupe,
    PRIMARY KEY  (nomcursus, groupe)
);


create table module (               -- un module est defini par son code
    code_module  varchar(20) primary key,
    libelle varchar(50)
);

create table creneau_type (         -- chaque module(code_module,groupe) 
                                    -- a une durée

    code_module  varchar(20) references module,
    groupe   varchar(10) references groupe,
    duree   numeric(1),
    primary key (code_module, groupe)
);

create table edt_sem (
    code_module  varchar(20) references module,
    groupe   varchar(10) references groupe,    
    sem     numeric(2) CHECK ( sem >= 1 and sem <= 52),
    jour    numeric(1) CHECK ( jour  >= 1 and jour <= 5),
    heured  numeric(1) CHECK ( heured >= 1 and heured <= 8),
    salle   varchar(10),
      primary key (code_module, groupe, sem), -- code_module, groupe, sem 
                                              --   -> jour, heured, duree
      unique (sem, jour, heured, salle ),      -- il n y a pas 2 cours en meme 
                                              -- temps dans la même salle
      foreign key (code_module,groupe) references creneau_type
    );




