CREATE TABLE personne(
    numpers integer PRIMARY KEY ,
    nom character varying(30),
    prenom character varying(30),
    pere integer REFERENCES personne(numpers),
    mere integer REFERENCES personne(numpers)
  )