--2
SELECT nom,
    prix,
    typeVoyage
FROM voyage
WHERE (
        typeVoyage = "RD"
        OR typeVoyage = "SD"
    )
    AND prix < 700
ORDER BY prix;

--3
SELECT v.nom,
    d.dateDepart
FROM voyage v
    JOIN etape e ON v.id = e.idVoyage
    JOIN ville v2 on e.idVille = v2.id
    JOIN depart don v.id = d.idVoyage
WHERE v2.nom = "Istanbul"
ORDER BY v.nom,
    d.dateDepart;

--4
SELECT distinct c.id,
    c.nom,
    c.prenom
FROM client c
    JOIN reservation r ON c.id = r.idClient
    JOIN voyage v ON v.id = r.idVoyage
    JOIN etape e ON v.id = e.idVoyage
    JOIN ville v2 on e.idVille = v2.id
    JOIN depart d ON d.idVoyage = v.id
    JOIN pays p ON p.code = v2.codePays
WHERE p.nom = "Egypte"
    or p.nom = "Tunisie"
    and d.dateDepart < '2023-12-31'
ORDER BY c.id;

--5 
--Il n'y a pas :(

--6
SELECT c.nom,
    c.prenom,
    v.nom,
    v.typeVoyage,
    d.dateDepart
FROM client c
    JOIN reservation r ON c.Id = r.idClient
    JOIN voyage v ONv.id = r.idVoyage
    JOIN depart d ON d.idVoyage = v.id
WHERE v.typeVoyage = "CT"
EXCEPT
SELECT c.nom,
    c.prenom,
    v.nom,
    v.typeVoyage,
    d.dateDepart
FROM client c
    JOIN reservation r ON c.id = r.idClient
    JOIN voyage v ON v.id = r.idVoyage
    JOIN depart d ON d.idVoyage = v.id
WHERE v.typeVoyage <> "CT"
ORDER BY d.dateDepart,
    v.typeVoyage;

--7
SELECT v2.nom,
    v.type Voyage,
    COUNT(v.id) AS "nombre de voyages"
FROM voyage v
    JOIN etape e ON v.id = e.idVoyage
    JOIN ville v2 on e.idVille = v2.id
GROUP BY v.typeVoyage,
    e.idVille 

--8 
SELECT v.nom,
    SUM(e.duree) AS "duree du voyage",
    v.typeVoyage
FROM voyage v
    JOIN etape e ON v.id = e.idVoyage
GROUP BY v.id,
    v.nom,
    v.typeVoyage
HAVING SUM(e.duree) >= 10
ORDER BY SUM(e.duree) 

--9
SELECT v.id,
    v.nom
FROM voyage v
WHERE v.id NOT IN (
        SELECT distinct v.id
        FROM voyage
            JOIN reservation r on v.id = r.idVoyage
    )
ORDER BY v.id 

--10
SELECT v.nom,
    d.dateDepart
FROM voyage v
    JOIN depart d ON v.id = d.idVoyage
WHERE type Voyage = "CT"
    and v.id NOT IN (
        SELECT v.id
        FROM voyage v
            JOIN etape e ON v.id = e.idVoyage
            JOIN ville v2 ON e.idVille = v2.id
            JOIN pays p ON v2.codePays = p.code
        WHERE p.nom = "Egypte"
    )
ORDER BY v.nom,
    d.dateDepart 
    
--11
SELECT v.nom,
    d.dateDepart
FROM voyage v
    JOIN depart d ON d.idVoyage = v.id
WHERE d.capacite > (
        SELECT count(*)
        FROM reservation r
        WHERE r.idVoyage = v.id
            and r.dateDepart = d.dateDepart
    ) 

--12
SELECT distinct c.nom,
    c.prenom,
    v.nom,
    d.dateDepart
FROM client c
    LEFT JOIN reservation r ON c.Id = r.idClient
    JOIN voyage v ON v.id = r.idVoyage
    JOIN depart d ON d.idVoyage = v.id
WHERE d.dateDepart < "2023-12-31"
    and d.dateDepart > "2023-01-01";
    
--13
SELECT v.nom,
    d.dateDepart,
    (
        d.capacite - (
            SELECT count(r.id)
            FROM reservation r
            WHERE r.idVoyage = v.id
                and r.dateDepart = d.dateDepart
        )
    ) as "Nombre de places restantes"
FROM voyage v
    JOIN depart d ON d.idVoyage = v.id
WHERE d.capacite > (
        SELECT count(*)
        FROM reservation r
        WHERE r.idVoyage = v.id
            and r.dateDepart = d.dateDepart
    )
ORDER BY 3 DESC 

--14
DROP view if exists temp;
create view temp as
SELECT SUM(e2.duree) as "duree_total",
    v2.id
FROM voyage v2
    JOIN etape e2 ON v2.id = e2.idVoyage
GROUP BY v2.id;
SELECTv.nom,
v.typeVoyage
FROM voyage v
    JOIN temp t on v.id = t.id
where t.duree_total = (
        SELECT max(duree_total)
        from temp
    )