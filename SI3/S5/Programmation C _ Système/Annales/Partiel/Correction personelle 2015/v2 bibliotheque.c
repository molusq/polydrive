#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NBLIVRES 1000

typedef struct {
    char titre[150];
    char nomAuteur[100];
    int anneeParution;
} livre_t;

typedef struct bibliotheque {
    livre_t livre;
    struct bibliotheque *suivant;
} bibliotheque_t;

/*
Le fait d'utiliser une liste chaînée permet d'optimiser
l'utilisation de la mémoire. Un nouvel élément est
créé seulement quand c'est nécessaire, contrairement à un
tableau de pointeurs.
La liste chaînée permet de supprimer la limite artificielle
de 1000 éléments.
*/

int livre_apres(int n, bibliotheque_t *bib) {
    int resultat = 0;
    bibliotheque_t *p = bib;
    while (p->suivant != NULL) {
        if (p->livre.anneeParution > n) {
            resultat++;
        }
        p = p->suivant;
    }
    return resultat;
}

void supprimer_livre(size_t index, bibliotheque_t *bib) {
    bibliotheque_t *courant = bib;
    
    for (int i = 0; i < index-1; ++i) {
        if (courant->suivant == NULL) return;
        courant = courant->suivant;
    }
    
    bibliotheque_t *supp_element = courant->suivant;
    courant->suivant = supp_element->suivant;
    
    free(supp_element);
}

int main() {
    printf("Hello World");
    return 0;
}
