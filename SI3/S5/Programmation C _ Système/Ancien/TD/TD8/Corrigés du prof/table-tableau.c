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

struct t_table {                // Implémentation avec un tableau
  struct mot *tab;
  int size;
  int nb_elem;
};

struct mot {                    // pour chaque mot: sa valeur et son nbre d'occ.
  char *word;
  int occur;
};


//
// Creation d'une table vide
//
Table creer_table(void) {
  Table t         = malloc(sizeof(struct t_table));
  struct mot *tab = malloc(ALLOC_SIZE * sizeof(struct mot));

  if (!t || !tab) {
     fprintf(stderr, "cannot allocate memory\n");
    return 0;
  }
  t->tab = tab;
  t->size = ALLOC_SIZE;
  t ->nb_elem = 0;
  return t;
}


//
// Ajout dans un tableau (ajustable)
// (Le résultat est le nombre d'occurences de elt)

static int add_word(Table *table, int index, char *str) {
  Table T           = *table;
  struct mot *mots = T->tab;
  int i, nb        = T->nb_elem;
  int size         = T->size;

  if (nb == size) {
    // La table est pleine, la "rallonger" avant d'essayer d'insérer str
    size *= 1.5;
    mots = realloc(mots, size*sizeof(struct mot));

    if (!mots) {
      fprintf(stderr, "cannot reallocate memory\n");
      return 0;
    }

    // conserver les nouvelles valeurs dans la table
    T->tab = mots;
    T->size  = size;
  }

  // Créer une copie du mot passé en paramètre
  char *mot = strdup(str);

  if (! mot) {
    fprintf(stderr, "cannot make a copy of '%s'\n", str);
    return 0;
  }

  // Décaler l'intervalle [i .. nb[ d'une case à droite
  for (i=nb; i > index; i--) {
    mots[i] = mots[i-1];
  }

  // Insérer le nouveau mot à la position index
  mots[index].word = mot;
  mots[index].occur = 1;

  // On a un mot de plus dans la table
  T->nb_elem += 1;

  return 1;                     // car ce mot apparaît une fois
}


int ajouter_table(Table *table, char *elt) {
  Table T          = *table;
  struct mot *mots = T->tab;
  int i, res, nb   = T->nb_elem;

  // Chercher dans "tab" le mot "elt"
  for (i=0; i < nb; i++) {
    res = strcmp(elt, mots[i].word);
    if (res < 0)
      break;
    if (res==0)         // trouvé ⇒ incrémenter le nombre d'occ. et terminer
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
  int i, nb        = table->nb_elem;

  for (i=0; i < nb; i++)
    fonction(mots[i].word, mots[i].occur);
}


// Impression des elements de la table
//      On utilise la fonction appliquer définie au-dessus
//
static void imprimer_aux(char *str, int nb) {
  printf("%s apparaît %d fois\n", str, nb);
}

void imprimer_table(Table table){
  appliquer_table( table, imprimer_aux);
}


//
// Recherche du nombre d'occurrences d'un element dans la table
//
int rechercher_table(Table table, char *elt) {
  struct mot *mots = table->tab;
  int i, nb        = table->nb_elem;

  for (i=0; i < nb; i++) {
    int n = strcmp(elt,mots[i].word);

    if (n == 0)
      return mots[i].occur;     // Trouvé
    if (n > 0)
      break;                    // Pas la peine de chercher plus loin
  }
  return 0;                     // Pas trouvé
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

  //libérer la table elle-même
  free(T);

  *table = NULL; // pas vraiment utile, mais pourquoi pas?
}
