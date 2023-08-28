#include "table.h"
#include <string.h>
#include <stdio.h>

//
// Fonctions elementaires de manipulation de la table
//
struct table
{
	char *mot;
	int nbocc;
	struct table *suiv;
};




/* Creation d'une table vide */
Table creer_table(void){
	return NULL;
}

/* Insertion d'un élément dans la table triée. Si l'élément est déjà
 * présent dans la table, le compteur d'occurrences est incrémenté.
 * La fonction renvoie le nombre actuel d'occurrences de elt */
int ajouter_table(Table *table, char *elt){
	struct table *p, *pp;
	for(p = *table; p != NULL && strcmp(elt, (*table) -> mot) > 0; pp = p, p = p -> suiv);
	if(strcmp(elt, p -> mot) == 0){
		p -> nbocc++;
	} else {
		struct table *t = malloc(sizeof(Table));
		t -> nbocc = 1;
		t -> mot = malloc(strlen(elt) + 1);
		strcpy(t -> mot, elt);
		pp -> suiv = t;
		t -> suiv = p;
	}
	return p -> nbocc;
}

/* Impression triée des éléments de la table */
/*void imprimer_table(Table table){
	for(; table != NULL; table = table -> suiv){
		printf("Mot %s, nombre d'occurences: %d", table -> mot, table -> nbocc);
	}
}

/* Appel d'une fonction sur chacun des éléments de la table */
//void appliquer_table(Table table, t_fonction fonction){}

/* Recherche du nombre d'occurrences d'un élément */
//int rechercher_table(Table table, char *elt){}

/* Destruction d'une table */
//void detruire_table(Table *table){}



