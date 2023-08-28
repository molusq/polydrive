
-- trigger qui verifie l'assertion: le pays du depot est celui de la marque


CREATE OR REPLACE FUNCTION checkpaysdepot()
  RETURNS trigger AS
$checkpaysdudepottrigger$
begin
if not exists
   (select m.id from marque m where m.id=new.idmarque and m.pays=new.pays)
   then raise exception 'pays du depot incorrect pour le depot % dans le pays %',
   	      new.numlegal,new.pays;
end if;
return new;
end;

$checkpaysdudepottrigger$
  LANGUAGE plpgsql ;

create  trigger   checkpaysdudepottrigger
before insert or update on depots
for each row
execute procedure checkpaysdepot();
