
/*
------------------------------------
init_schema_edt.sql 
------------------------------------
*/

-- ----------------------------------
-- tables de donnees
-- ----------------------------------

\copy creneau_init from creneaux.csv delimiter ';' csv   

-- ----------------------------------
-- Tables indépendantes de la semaine
-- ----------------------------------

insert into module
 select distinct C.code_module, C.libelle from creneau_init C;

insert into groupe
    select distinct C.groupe from creneau_init C;

insert into creneau_type
    select distinct C.code_module, C.groupe, C.duree from creneau_init C;

insert into cursus
    select distinct C.cursus from creneau_init C;

insert into cursus_groupe
    select distinct C.cursus, C.groupe from creneau_init C;

