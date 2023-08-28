/*                                              -*- coding: utf-8 -*-
 *
 * Une implémentation de table triée avec un arbre
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  8-Dec-2020 13:32 (eg)
 * Last file update:  8-Dec-2020 15:13 (eg)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "table.h"


struct t_table  {                  // un arbre ici
  char *ident;
  int nb;
  struct t_table *left, *right;
};


//
// Creation d'une table vide
//
Table creer_table(void) {
  return NULL; /* Simple, isn't it? */
}

//
// Recherche du nombre d'occurrences d'un element dans la table
//
int rechercher_table(Table table, char *v) {
   if (!table)
    return 0;
   else {
     int res =  strcmp(v, table->ident);
     if (res == 0)
       // l'élément est dans la table
       return table->nb;
    else {
      Table subtree = (res < 0) ? table->left : table->right;
      return rechercher_table(subtree, v);
    }
  }
}

//
// Ajout dans une liste chaînée triée.
// (Le résultat est le nombre d'occurences de elt)
//

static Table add(Table t, char *v, Table *node) {
  if (!t) {                                   // ⇔ t == NULL
    // create a new tree with a single value
    t  = malloc(sizeof(struct t_table));
    if (!t) {
      printf("Allocation error\n");
      exit(1);
    }
    t->ident = strdup(v);
    t->nb    = 1; 
    t->left  = t->right = NULL;
    *node = t;    // Retain the node where v lies
  } else {
    // we have a tree
    int comp = strcmp(v, t->ident);
    if (comp == 0) {
      // incrementer t->nb
      t->nb += 1;
      *node = t;  // Retain the node where v lies
    }
    else if (comp < 0)
      // insérer à gauche
      t->left = add(t->left, v, node);
    else
      // insérer à droite
      t->right = add(t->right, v, node);
  }
  return t;
}


int ajouter_table(Table *table, char *elt) {
  Table item;
  *table = add(*table, elt, &item);
  // On est sûr que item est ≠ NULL
  return  item->nb;
}

//
// Application d'une fonction aux éléments de la table
//
void appliquer_table(Table table, t_fonction fonction) {
  if (table) {
    appliquer_table(table->left, fonction);
    fonction(table->ident, table->nb);
    appliquer_table(table->right, fonction);
  }
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
// Destruction d'une table
//
void detruire_table(Table *table) {
  if (*table) {
    detruire_table( &((*table)->left)  );
    detruire_table( &((*table)->right) );
    free((*table)->ident);
    free(*table);
    *table = NULL; // pas vraiment utile, mais pourquoi pas?
  }
 }
