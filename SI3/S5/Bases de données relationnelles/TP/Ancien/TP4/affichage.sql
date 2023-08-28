/*
-------------------------------------------------
Table pour affichage de EDT d'une semaine
-------------------------------------------------
*/

drop table IF EXISTS  affichage;

create table affichage (
    code_module  varchar(20) references module,
    groupe  varchar(10),
    jour    numeric(1) ,
    heured  numeric(2) , 
    duree  numeric(2),
    ligne   numeric(2),
    salle   varchar(10),
   unique (code_module, groupe),
    primary key (jour,ligne, heured)
);


/*
-------------------------------------------------
Attribution des droits (necessaires pour l'acces
via le server resin)
-------------------------------------------------
*/



grant select, insert, delete, update on edt_sem, creneau_type, 
      cursus, cursus_groupe, module, affichage to invited;

/*
revoke all on edt_sem, creneau_type, 
      cursus, cursus_groupe, module, affichage FROM invited;
*/