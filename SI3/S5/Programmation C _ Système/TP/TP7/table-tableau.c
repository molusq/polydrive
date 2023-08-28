/*                                              -*- coding: utf-8 -*-
 *
 * Une implémentation de table triée avec un tableau alloué
 * dynamiquement
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  5-Dec-2013 17:29 (eg)
 * Last file update: 12-Dec-2017 12:26 (eg)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "table.h"

#define ALLOC_SIZE 4

// Cette implémentation passe par un tableau qui est alloué dynamiquement 
// (et qui est rallongé, lorsqu’il est plein).
// Pour la table nous allons utiliser la structure t_table suivante:
struct t_table {  // Implémentation avec un tableau
  struct mot *tab;
  int size;
  int nb_elem;
};

// avec pour chaque mot de la table un couple (chaîne de caractères et c
// ompteur du nombre d’apparitions):
struct mot {  // pour chaque mot: sa valeur et son nbre d'occ.
  char *word;
  int occur;
};

// Du coup, la fonction de création est un peu plus compliquée que précédemment; elle doit
//  - allouer un tableau d’objets de type struct mot (taille au départ: ALLOC_SIZE)
//  - allouer une structure t_table pour y ranger ce tableau et les informations sur la 
//    longueur de ce tableau et le nombre d’éléments qu’il contient (resp. ALLOC_SIZE et 0).
// La fonction de création de table:

//
// Creation d'une table vide
//
Table creer_table(void) {
  Table t = malloc(sizeof(struct t_table));
  struct mot *tab = malloc(ALLOC_SIZE * sizeof(struct mot));

  if (!t || !tab) {
    fprintf(stderr, "cannot allocate memory\n");
    return 0;
  }
  t->tab = tab;
  t->size = ALLOC_SIZE;
  t->nb_elem = 0;
  return t;
}

// Comme précédemment, la seule fonction difficile est la fonction d’insertion dans la table. 
// Celle-ci est donnée ci-dessous.
// Cette fonction utilise ici la fonction add_word qui permet d’ajouter un mot dans le tableau 
// tab à une position donnée. Nous avons deux problèmes ici:
//  - les éléments situés de l’indice i à nb_elemn-1 doivent être décalés vers la droite.
//  - l’ajout d’un mot dans tab peut provoquer un débordement. Il faut donc, dans ce cas, 
//    "rallonger" ce tableau avant de réaliser l’insertion.
// Note:
//    La fonction add_word est déclaré ici static, car c’est un utilitaire de notre module de 
//    tables qui n’a pas besoin d’être accessible depuis l’extérieur du module. 

//
// Ajout dans un tableau (ajustable)
// (Le résultat est le nombre d'occurences de elt)

static int add_word(Table *table, int index, char *str) {
  Table T = *table;
  struct mot *mots = T->tab;
  int i, nb = T->nb_elem;
  int size = T->size;

  if (nb == size) {
    // La table est pleine, la "rallonger" avant d'essayer d'insérer str
    size *= 1.5;
    mots = realloc(mots, size * sizeof(struct mot));

    if (!mots) {
      fprintf(stderr, "cannot reallocate memory\n");
      return 0;
    }

    // conserver les nouvelles valeurs dans la table
    T->tab = mots;
    T->size = size;
  }

  // Créer une copie du mot passé en paramètre
  char *mot = strdup(str);

  if (!mot) {
    fprintf(stderr, "cannot make a copy of '%s'\n", str);
    return 0;
  }

  // Décaler l'intervalle [i .. nb[ d'une case à droite
  for (i = nb; i > index; i--) {
    mots[i] = mots[i - 1];
  }

  // Insérer le nouveau mot à la position index
  mots[index].word = mot;
  mots[index].occur = 1;

  // On a un mot de plus dans la table
  T->nb_elem += 1;

  return 1;  // car ce mot apparaît une fois
}

int ajouter_table(Table *table, char *elt) {
  Table T = *table;
  struct mot *mots = T->tab;
  int i, res, nb = T->nb_elem;

  // Chercher dans "tab" le mot "elt"
  for (i = 0; i < nb; i++) {
    res = strcmp(elt, mots[i].word);
    if (res < 0)
      break;
    if (res == 0)  // trouvé ⇒ incrémenter le nombre d'occ. et terminer
      return ++mots[i].occur;
  }

  // Si on est là, ajouter une un nouvel élément à mettre à l'indice i
  return add_word(table, i, elt);
}

//
// Application d'une fonction aux elements de la table
//
void appliquer_table(Table table, t_fonction fonction) {
  struct mot *mots = table->tab;
  int i, nb = table->nb_elem;

  for (i = 0; i < nb; i++)
    fonction(mots[i].word, mots[i].occur);
}

// Impression des elements de la table
//      On utilise la fonction appliquer définie au-dessus
//
static void imprimer_aux(char *str, int nb) {
  printf("%s apparaît %d fois\n", str, nb);
}

void imprimer_table(Table table) {
  appliquer_table(table, imprimer_aux);
}

//
// Recherche du nombre d'occurrences d'un element dans la table
//
int rechercher_table(Table table, char *elt) {
  struct mot *mots = table->tab;
  int i, nb = table->nb_elem;

  for (i = 0; i < nb; i++) {
    int n = strcmp(elt, mots[i].word);

    if (n == 0)
      return mots[i].occur;  // Trouvé
    if (n > 0)
      break;  // Pas la peine de chercher plus loin
  }
  return 0;  // Pas trouvé
}

//
// Destruction d'une table
//
void detruire_table(Table *table) {
  int i;
  Table T = *table;

  // Destruction des chaînes allouées par strdup
  for (i = 0; i < T->nb_elem; i++)
    free(T->tab[i].word);

  // libérer le tableau de mots (le tableau alloué dans le t_table)
  free(T->tab);

  // libérer la table elle-même
  free(T);

  *table = NULL;  // pas vraiment utile, mais pourquoi pas?
}

// Remarque:
//  Cette deuxième implémentation est un peu moins efficace en temps que l’implémentation 
//  précédente, comme on peut le voir sur les exécutions suivantes (/etc/services est un 
//  fichier qui contient environ 10 000 mots différents).

// $ time impl-1 < /etc/services > /dev/null
//    User: 0.27s
//  System: 0.00s
// Elapsed: 0.27s

// $ time impl-2 < /etc/services > /dev/null
//    User: 0.42s
//  System: 0.00s
// Elapsed: 0.42s

// Cette différence s’explique par l’algorithme d’insertion qui est plus inefficace 
// (nombreux décalages).