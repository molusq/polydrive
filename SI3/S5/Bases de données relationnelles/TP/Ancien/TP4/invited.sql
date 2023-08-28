 DROP database IF EXISTS   invited;
 revoke all on edt_sem, creneau_type, 
      cursus, cursus_groupe, module, affichage FROM invited;
 DROP user IF EXISTS invited;
 create  user invited;
 create database invited with owner invited;
 alter user invited with password'invited';
 alter user  invited set search_path to public ;

