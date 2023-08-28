

/*
------------------------------------
fill_semaine_edt.sql 
------------------------------------
*/

-- ----------------------------------
-- Tables de données
-- ----------------------------------


\copy edt_init from edt_49.csv delimiter ';' csv


-- ----------------------------------
-- Table Semaine
-- ----------------------------------


 
insert into edt_sem   select * from edt_init;

