#include "table.h"
#include <stdlib.h>
#include <stdio.h>
//
// Fonctions elementaires de manipulation de la table
//

typedef struct table {
    char* elt;
    Table tete;
};

/* Creation d'une table vide */
Table creer_table(void) {
    Table t = malloc(sizeof(Table));
    t->tete = NULL;
    return t;
}

/* Insertion d'un élément dans la table triée. Si l'élément est déjà
 * présent dans la table, le compteur d'occurrences est incrémenté.
 * La fonction renvoie le nombre actuel d'occurrences de elt */
int ajouter_table(Table *table, char *elt) {
    Table t = *table;
    if (t->tete == NULL) {
        t->tete = malloc(sizeof(Table));
        t->tete->elt = elt;
        t->tete->tete = NULL;
        return 1;
    }
    Table tete = t->tete;
    while (tete->tete != NULL) {
        tete = tete->tete;
    }
    tete->tete = malloc(sizeof(Table));
    tete->tete->elt = elt;
    tete->tete->tete = NULL;
    return 1;
}

/* Impression triée des éléments de la table */
void imprimer_table(Table table) {
    Table tete = table->tete;
    while (tete != NULL) {
        printf("%s, ", tete->elt);
        tete = tete->tete;
    }
}

/* Appel d'une fonction sur chacun des éléments de la table */
void appliquer_table(Table table, t_fonction fonction) {
    Table tete = table->tete;
    while (tete != NULL) {
        fonction(tete->elt, 1);
        tete = tete->tete;
    }
}

/* Recherche du nombre d'occurrences d'un élément */
int rechercher_table(Table table, char *elt) {
    Table tete = table->tete;
    while (tete != NULL) {
        if (tete->elt == elt) {
            return 1;
        }
        tete = tete->tete;
    }
    return 0;
}

/* Destruction d'une table */
void detruire_table(Table *table) {
    Table tete = (*table)->tete;
    while (tete != NULL) {
        Table tmp = tete;
        tete = tete->tete;
        free(tmp);
    }
    free(*table);
}
