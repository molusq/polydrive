CREATE TABLE personne(
    numpers integer PRIMARY KEY ,
    nom character varying(30),
    prenom character varying(30),
    pere integer REFERENCES personne(numpers),
    mere integer REFERENCES personne(numpers)
  )

-- cette table contient des informations sur des personnages.
-- mere et père sont  des entiers qui contiennent le numpers de la mere et  du père du personnage.
-- si le pere ou la mere sont inconnus, ces entiers ne sont pas renseignés.