DROP TABLE if exists qualification, calendrier, vol, pilote, avion CASCADE;

CREATE TABLE pilote
(
  IdPilote integer PRIMARY KEY ,
  NomPilote character varying(20)
);



CREATE TABLE avion
(
  IdAvion integer PRIMARY KEY,
  TypeAvion character varying(10),
  Portee integer
  
);


CREATE TABLE vol
(
  CodeVol character varying(10) PRIMARY KEY,
  VilleDepart character varying(10),
  VilleArrivee character varying(10),
  Distance integer,
  HeureDepart time without time zone,
  HeureArrivee time without time zone
  
);

CREATE TABLE calendrier
(
  Jour character varying(10),
  IdPilote integer,
  IdAvion integer REFERENCES avion,
  CodeVol character varying(10) REFERENCES vol,
  PRIMARY KEY(Jour, codeVol)
  
);
CREATE TABLE qualification
(IdPilote integer REFERENCES pilote,
  TypeAvion character varying(10),
  PRIMARY KEY(IdPilote,TypeAvion)
);


INSERT INTO avion (idavion, typeavion, portee) VALUES (1, 'A300', 2500);
INSERT INTO avion (idavion, typeavion, portee) VALUES (2, 'A310', 3000);
INSERT INTO avion (idavion, typeavion, portee) VALUES (3, 'B235', 1000);




INSERT INTO vol (codevol, villedepart, villearrivee, distance, heuredepart, heurearrivee) VALUES ('VEPU2', 'Paris ', 'Bordeaux', 700, '09:00:00', '10:00:00');
INSERT INTO vol (codevol, villedepart, villearrivee, distance, heuredepart, heurearrivee) VALUES ('VEPU1', 'Nice', 'Paris', 1100, '07:00:00', '08:15:00');
INSERT INTO vol (codevol, villedepart, villearrivee, distance, heuredepart, heurearrivee) VALUES ('VEPU7', 'Paris', 'Nice', 1100, '07:00:00', '08:00:00');
INSERT INTO vol (codevol, villedepart, villearrivee, distance, heuredepart, heurearrivee) VALUES ('VEPU3', 'Paris', 'Reims', 400, '10:00:00', '10:45:00');
INSERT INTO vol (codevol, villedepart, villearrivee, distance, heuredepart, heurearrivee) VALUES ('VEPU6', 'Reims', 'Nantes', 400, '10:55:00', '11:15:00');



INSERT INTO calendrier (jour, idpilote, idavion, codevol) VALUES ('lundi', 1, 1, 'VEPU1');
INSERT INTO calendrier (jour, idpilote, idavion, codevol) VALUES ('mardi', 2, 2, 'VEPU2');
INSERT INTO calendrier (jour, idpilote, idavion, codevol) VALUES ('mardi', 3, 1, 'VEPU1');
INSERT INTO calendrier (jour, idpilote, idavion, codevol) VALUES ('lundi', 1, 1, 'VEPU2');
INSERT INTO calendrier (jour, idpilote, idavion, codevol) VALUES ('lundi', 3, 3, 'VEPU3');
INSERT INTO calendrier (jour, idpilote, idavion, codevol) VALUES ('lundi', 3, 3, 'VEPU6');
INSERT INTO calendrier (jour, idpilote, idavion, codevol) VALUES ('lundi', 3, 3, 'VEPU7');



INSERT INTO pilote (idpilote, nompilote) VALUES (1, 'Charlie');
INSERT INTO pilote (idpilote, nompilote) VALUES (2, 'Tango');
INSERT INTO pilote (idpilote, nompilote) VALUES (3, 'Papa');



INSERT INTO qualification (idpilote, typeavion) VALUES (1, '1');
INSERT INTO qualification (idpilote, typeavion) VALUES (1, '2');
INSERT INTO qualification (idpilote, typeavion) VALUES (2, '1');
INSERT INTO qualification (idpilote, typeavion) VALUES (3, '2');
--4.1
select CodeVol from calendrier Natural JOIN  vol Natural JOIN avion
       where vol.Distance > avion.Portee;

-- ou
select C.CodeVol from calendrier C, vol V
       where C.CodeVol=V.CodeVol and
       	     V.Distance > (select A.Portee from avion A
	     		where A.IdAvion = C.IdAvion);
/*
 codevol 
---------
 VEPU7
(1 row)
*/
--4.2
select nomPilote from  pilote
	where idPilote not in (
				select calendrier.IdPilote from  avion, calendrier,vol 
					where avion.TypeAvion='A300' 
					  and avion.IdAvion = calendrier.IdAvion
					  and calendrier.CodeVol=vol.CodeVol);
					 
/*
 nompilote 
-----------
 Tango
(1 row)
*/


-- 4.3
select CodeVol from calendrier 
	except
	select C3. CodeVol from 
	(select C1.CodeVol, C2.Jour from calendrier C1, calendrier C2
			except
	select C.CodeVol, C.Jour from calendrier C) as C3;
--ou

select CodeVol from calendrier
       	     	     		group by CodeVol
       	     	     		having count(*) = (select count(distinct jour) from calendrier);
				
/*
codevol 
---------
 VEPU1
 VEPU2
(2 rows)
*/


	
--4.4
select CodeVol from vol where HeureDepart in 
	(select min(HeureDepart) from vol);
--ou
select CodeVol from vol group by Codevol having min(heuredepart) = vol.heuredepart;
/*
 codevol 
---------
 VEPU1
 VEPU7
(2 rows)
*/	

--4.5
      
drop view if exists premierVolDuJour1;	
create view premierVolDuJour1 as select distinct  c1.IdAvion, c1.Jour, c1.CodeVol 
             from  calendrier c1, vol v1
             where c1.CodeVol = v1.CodeVol and not exists (
	     	   select * from vol v2, calendrier c2
		    	  where c2.CodeVol = v2.CodeVol and c1.jour=c2.jour and c1.IdAvion=c2.IdAvion
		   	  	and v1.heuredepart > v2.heuredepart)
	     order by Jour;
select * from premierVolDuJour1;

--ou
drop view if exists premierAvionDuJour;	
create view premierAvionDuJour as 
select C.Jour, C.IdAvion, Min(HeureDepart) as HD
       From calendrier C, vol V
      where  V.CodeVol = C.CodeVol
      group by C.Jour,C. IdAvion;
      
drop view if exists premierVolDuJour;	
create view premierVolDuJour as select distinct P.Jour, P.IdAvion, C.CodeVol 
             from  premierAvionDuJour P, calendrier C, vol V
             where  P.HD = V.HeureDepart and C.IdAvion = P.IdAvion and C.CodeVol = V.CodeVol
	     order by Jour;
select * from premierVolDuJour;

-- 4.6

drop view if exists premierVolDuJourComplet;	
create view premierVolDuJourComplet as 
	select IdAvion, Jour, CodeVol from 
		(select distinct A.IdAvion, C.Jour from  calendrier C, avion A ) as A
		left join premierVolDuJour using (IdAvion, Jour);
select * from premierVolDuJourComplet;
drop view if exists premierVolDuJourComplet1;	
create view premierVolDuJourComplet1 as 
	select IdAvion, Jour, CodeVol from 
		(select distinct A.IdAvion, C.Jour from  calendrier C, avion A ) as A
		full join premierVolDuJour using (IdAvion, Jour);
select * from premierVolDuJourComplet1;
/*
 idavion | jour  | codevol 
---------+-------+---------
       1 | lundi | VEPU1
       1 | mardi | VEPU1
       2 | lundi | 
       2 | mardi | VEPU2
       3 | lundi | VEPU7
       3 | mardi | 
(6 rows)
*/

--4.7
drop view if exists Connexions;
create view Connexions as 
with recursive connexion (Jour,VilleDepart, VilleArrivee, NombreEtapes, HeureDepart, HeureArrivee) as (
	select Jour, VilleDepart, VilleArrivee, 1, HeureDepart, HeureArrivee 
		from vol, calendrier where vol.CodeVol=calendrier.CodeVol
	union
	select C.Jour, connexion.VilleDepart, V.VilleArrivee, 
			NombreEtapes+1, connexion.HeureDepart, V.HeureArrivee
		from vol V,calendrier C,connexion 
		where  V.CodeVol=C.CodeVol and
		connexion.VilleArrivee=V.VilleDepart
		and connexion.HeureArrivee < V.HeureDepart
		and C.Jour=connexion.Jour
		)
		
select  Jour, VilleDepart, VilleArrivee, NombreEtapes, HeureDepart, HeureArrivee from connexion   ;
select * from Connexions order by Jour, HeureDepart;
		
/*
 jour  | villedepart | villearrivee | nombreetapes | heuredepart | heurearrivee 
-------+-------------+--------------+--------------+-------------+--------------
 lundi | Nice        | Paris        |            1 | 07:00:00    | 08:15:00
 lundi | Paris       | Nice         |            1 | 07:00:00    | 08:00:00
 lundi | Nice        | Reims        |            2 | 07:00:00    | 10:45:00
 lundi | Nice        | Nantes       |            3 | 07:00:00    | 11:15:00
 lundi | Paris       | Bordeaux     |            1 | 09:00:00    | 10:00:00
 lundi | Paris       | Reims        |            1 | 10:00:00    | 10:45:00
 lundi | Paris       | Nantes       |            2 | 10:00:00    | 11:15:00
 lundi | Reims       | Nantes       |            1 | 10:55:00    | 11:15:00
 mardi | Nice        | Paris        |            1 | 07:00:00    | 08:15:00
 mardi | Paris       | Bordeaux     |            1 | 09:00:00    | 10:00:00
(10 rows)
*/
					  
