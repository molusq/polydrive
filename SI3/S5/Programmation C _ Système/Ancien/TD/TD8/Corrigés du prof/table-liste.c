/*                                              -*- coding: utf-8 -*-
 *
 * Une implémentation de table triée avec une liste simplement chaînée
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  5-Dec-2013 17:29 (eg)
 * Last file update: 18-Dec-2020 17:18 (eg)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "table.h"


struct t_table  {          // liste simplement chaînee ordonnée
  char *ident;
  int nb;
  struct t_table *suivant; // Pointeur vers l'élément suivant
};


//
// Creation d'une table vide
//
Table creer_table(void) {
  return NULL; /* Simple, isn't it? */
}


//
// Ajout dans une liste chaînée triée.
// (Le résultat est le nombre d'occurences de elt)
//
int ajouter_table(Table *table, char *elt) {
  Table prev, cur;

  // On va réaliser une insertion dans une liste triée simplement chaînée
  for (prev=NULL, cur=*table; cur ; prev=cur, cur=cur->suivant) {
    int res = strcmp(elt, cur->ident);
    if (res<0)          //  elt < élément pointé par cur ⇒ insérer entre prev et cur
      break;
    if (res==0)         // trouvé ⇒ incrémenter le nombre d'occ. et terminer
      return ++(cur->nb);
  }

  // Si on est là c'est que l'on a un nouvel élément à mettre dans la liste
  Table temp  = (Table) malloc(sizeof(struct t_table));
  char *mot   = strdup(elt); // on place une copie de elt dans la table

  if (!temp || !mot) {                             // malloc ou strdup ont échoué
    fprintf(stderr, "cannot allocate memory\n");
    return 0;
  }

  temp->ident   = mot;
  temp->nb      = 1;
  temp->suivant = cur;

  if (prev)
    prev->suivant = temp;               // ajout en milieu ou fin de liste
  else
    *table = temp;                      // ajout en début de liste
  return 1;                             // première occurence dans tous les cas
}


//
// Application d'une fonction aux éléments de la table
//
void appliquer_table(Table table, t_fonction fonction) {
  for (; table; table=table->suivant)
    fonction(table->ident, table->nb);
}


// Impression des éléments de la table
//      On utilise la fonction appliquer définie au-dessus
//      Remarque: la fonction imprimer_aux est statique pour ne pas être visible
//                en dehors de ce fichier
static void imprimer_aux(char *str, int nb) {
  printf("%s apparaît %d fois\n", str, nb);
}

void imprimer_table(Table table) {
  appliquer_table( table, imprimer_aux);
}


//
// Recherche du nombre d'occurrences d'un element dans la table
//
int rechercher_table(Table table, char *elt) {
  for ( ; table; table=table->suivant) {
    int n = strcmp(elt,table->ident);

    if (n == 0)
      return table->nb;         // Trouvé
    if (n > 0)
      break;                    // Pas la peine de chercher plus loin
  }
  return 0;                     // Pas trouvé
}


//
// Destruction d'une table
//
void detruire_table(Table *table) {
  Table cur, suiv;

  for (cur = *table; cur; cur=suiv) {
    suiv = cur->suivant;
    free(cur->ident);
    free(cur);
  }
  *table = NULL; // pas vraiment utile, mais pourquoi pas?
}
