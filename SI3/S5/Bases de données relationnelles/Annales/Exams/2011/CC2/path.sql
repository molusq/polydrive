DROP TABLE fichiers;
CREATE TABLE fichiers(
          id SERIAL PRIMARY KEY,
          name VARCHAR,
          parent_id INTEGER REFERENCES fichiers(id)
        );

INSERT INTO  fichiers VALUES(1, '');
INSERT INTO  fichiers VALUES(2, 'rep1',1);
INSERT INTO  fichiers VALUES(3, 'rep2',2);
INSERT INTO  fichiers VALUES(4, 'fich1',3);



WITH RECURSIVE path(name, path, id, parent_id) AS (
          SELECT name, '', id, parent_id FROM  fichiers WHERE id = 1
          UNION
          SELECT
            fichiers.name,
            parentpath.path ||  '/' || fichiers.name,
            fichiers.id,  fichiers.parent_id
          FROM fichiers, path as parentpath
          WHERE fichiers.parent_id = parentpath.id)
        SELECT id, name, path FROM path; 

/*
DROP TABLE
psql:./path.sql:6: NOTICE:  CREATE TABLE will create implicit sequence "fichiers_id_seq" for serial column "fichiers.id"
psql:./path.sql:6: NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "fichiers_pkey" for table "fichiers"
CREATE TABLE
INSERT 0 1
INSERT 0 1
INSERT 0 1
INSERT 0 1
 id | name  |       path       
----+-------+------------------
  1 |       | 
  2 | rep1  | /rep1
  3 | rep2  | /rep1/rep2
  4 | fich1 | /rep1/rep2/fich1
(4 rows)



*/