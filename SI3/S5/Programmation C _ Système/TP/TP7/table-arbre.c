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

// La représentation d’une table avec un arbre est assez similaire à celle que l’on 
// avait avec une liste: comme pour une liste un arbre est représenté par un pointeur 
// sur une structure. La seule différence, est que cette structure contient ici deux 
// pointeurs, plutôt qu’un seul. On obtient donc pour le type struct_t_table:

struct t_table {  // un arbre ici
  char *ident;
  int nb;
  struct t_table *left, *right;
};

// La fonction de création de table est bien évidemment identique à la fonction que 
// l’on avait pour l’implémentation avec des listes.

//
// Creation d'une table vide
//
Table creer_table(void) {
  return NULL; /* Simple, isn't it? */
}

// Commençons par implémenter la fonction de recherche dans l’arbre. Cette fonction 
// peut être construite sur le modèle de la fonction find_tree du TD précédent (la 
// seule vraie différence est qu’on utilise ici strcmp pour les comparaisons puisqu’on 
// travaille sur des chaînes). On obtient donc:

//
// Recherche du nombre d'occurrences d'un element dans la table
//
int rechercher_table(Table table, char *v) {
  if (!table)
    return 0;
  else {
    int res = strcmp(v, table->ident);
    if (res == 0)
      // l'élément est dans la table
      return table->nb;
    else {
      Table subtree = (res < 0) ? table->left : table->right;
      return rechercher_table(subtree, v);
    }
  }
}

// Pour ajouter la valeur dans la table on peut s’inspirer de la fonction pour ajouter 
// un élément dans un arbre que l’on avait vue dans la feuille précédente. On obtient 
// la fonction add suivante.
//
// static Table add(Table t, char *v) {
//   if (!t) {                                   // ⇔ t == NULL
//     // create a new tree with a single value
//     t  = malloc(sizeof(struct t_table));
//     if (!t) {
//       printf("Allocation error\n");
//       exit(1);
//     }
//     t->ident = strdup(v);
//     t->nb    = 1; 
//     t->left  = t->right = NULL;
//   } else {
//     // we have a tree
//     int comp = strcmp(v, t->ident);
//     if (comp == 0)
//       // incrementer t->nb
//       t->nb += 1;
//     else if (comp < 0)
//       // insérer à gauche
//       t->left = add(t->left, v);
//     else
//       // insérer à droite
//       t->right = add(t->right, v);
//   }
//   return t;
// }
// Cette fonction renvoie un arbre où la valeur v a été rajoutée dans l’arbre t donné 
// en paramètre. Par rapport à la version d’ajout du TD précédent, on se contente ici 
// d’incrémenter la valeur t->nb si l’élément est déjà présent dans l’arbre (au lieu 
// d’ajouter un nœud systématiquement).

// La fonction précédente, bien que correcte, n’est pas terrible puisqu’elle implique 
// deux parcours de l’arbre: un pour ajouter l’élément et un autre pour chercher cette 
// valeur. L’idée ici est d’avoir une fonction d’ajout dans l’arbre qui ait un paramètre 
// supplémentaire (node) dans lequel la fonction range le nœud qui contient v. Ce nœud 
// est soit trouvé, soit créé. On obtient donc la nouvelle fonction add:

//
// Ajout dans une liste chaînée triée.
// (Le résultat est le nombre d'occurences de elt)
//
static Table add(Table t, char *v, Table *node) {
  if (!t) {  // ⇔ t == NULL
    // create a new tree with a single value
    t = malloc(sizeof(struct t_table));
    if (!t) {
      printf("Allocation error\n");
      exit(1);
    }
    t->ident = strdup(v);
    t->nb = 1;
    t->left = t->right = NULL;
    *node = t;  // Retain the node where v lies
  } else {
    // we have a tree
    int comp = strcmp(v, t->ident);
    if (comp == 0) {
      // incrementer t->nb
      t->nb += 1;
      *node = t;  // Retain the node where v lies
    } else if (comp < 0)
      // insérer à gauche
      t->left = add(t->left, v, node);
    else
      // insérer à droite
      t->right = add(t->right, v, node);
  }
  return t;
}

// La fonction ajouter_table peut donc s’écrire maintenant simplement comme:

int ajouter_table(Table *table, char *elt) {
  Table item;
  *table = add(*table, elt, &item);
  // On est sûr que item est ≠ NULL
  return item->nb;
}

// Cette fonction se calque sur la fonction d’impression des arbres vue précédemment:

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
  appliquer_table(table, imprimer_aux);
}

//
// Destruction d'une table
//
void detruire_table(Table *table) {
  if (*table) {
    detruire_table(&((*table)->left));
    detruire_table(&((*table)->right));
    free((*table)->ident);
    free(*table);
    *table = NULL;  // pas vraiment utile, mais pourquoi pas?
  }
}

// Performances
// Au niveau des performances, on avait vu que la version avec des listes 
// était plus efficace. On avait:
//  $ time impl-1 < /etc/services > /dev/null
//      User: 0.27s
//    System: 0.00s
//   Elapsed: 0.27s

// La version avec des arbres est O(log n) au lieu de O(n). On voit tout 
// de suite que c’est bien plus efficace:
// $ time impl-3 < /etc/services > /dev/null
//    Job: impl-3 < /etc/services > /dev/null
//    User: 0.02s
//  System: 0.01s
// Elapsed: 0.02s

// Bien sûr, on ne peut pas se contenter d’un seul jeu de données pour une 
// réelle comparaison, mais on voit ici qu’on a été en gros 13 fois plus vite 
// sur ce jeu de données avec des arbres par rapport à des listes!!