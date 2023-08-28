-- Exemple cours
DROP TABLE IF EXISTS vols;
CREATE TABLE vols (
       airline char(2),
       departure char(3),
       arrival char(3),
       dtime time,
       atime time) ;

INSERT INTO vols VALUES ('UA','SF','DEN','9:30','12:30');
INSERT INTO vols VALUES ('UA','DEN','CHI','15:30','18:30');
INSERT INTO vols VALUES ('UA','CHI','NY','18:30','21:30');
INSERT INTO vols VALUES ('AA','CHI','NY','19:00','22:00');
INSERT INTO vols VALUES ('AA','SF','DAL','9:00','14:30');
INSERT INTO vols VALUES ('AA','DAL','NY','15:30','19:30');
INSERT INTO vols VALUES ('UA','DEN','DAL','14:00','17:00');
INSERT INTO vols VALUES ('AA','DAL','CHI','15:30','17:30');

