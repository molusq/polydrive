<!-- -*- mode: markdown -*- -->

La calculatrice: un frontend & trois backends
=============================================


Ce répertoire contient un squelette pour la calculatrice. La commande
make permet de construire trois exécutables:
- calc1: la version qui interprète directement une expression (ce
  fichier est pratiquement complet)
- calc2: la version qui produit du code pour une machine à pile fictive (à construire)
- calc3: la version qui produit un graphe au format `dot` (à  construire)

Les fichiers du répertoire
    * calc.c: contient principalement le code pour construire/détruire
    les noeuds de l'arbre.
    * calc.h: définition des structures de données et macros pour la
      gestions des nœuds de l'arbre
    * code.h: ne contient que le prototype de la fonction `produce_code`
    * code1.c, code2.c, code3.c: les trois version du générateur de code:
    * lexical.l: le source flex de la grammaire lexicale la calculatrice
    * syntax.y:  le source yacc de la grammaire de la calculatrice.


Le répertoire `Resources` contient quatre programmes de test et un
interprète pour la machine à pile utilisée par `calc2`. Pour chaque
programme de test (suffixé par `.in`), on a le code produit par calc2
(suffixé par `.as`) et le graphe du programme (suffixé par `.dot`).

*Note:**
- Les programmes `fact.in`, `puiss.in` et `ifwhile.in` n'utilisent pas
la primitive `read`. 
- Par contre, `read.in` utilise la primitive `read`

Pour exécuter l'interprète sur un fichier assembleur, il suffit de
lancer le programme `interp` avec en paramètre le fichier à exécuter:

```
     $ ./interp fact.as
     479001600.0
     *stop*
     $
```

