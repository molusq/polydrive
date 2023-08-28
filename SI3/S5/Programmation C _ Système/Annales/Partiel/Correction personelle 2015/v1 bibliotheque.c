#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NBLIVRES 1000

typedef struct {
    char titre[150];
    char nomAuteur[100];
    unsigned int anneeParution;
} livre_t;

//typedef livre_t bibliotheque_t[NBLIVRES];
typedef struct {
    livre_t *livres[NBLIVRES];
    size_t compteur;
    
} bibliotheque_t;

void initialiser_bibliotheque(bibliotheque_t *b) {
    memset(b->livres, 0, sizeof(b->livres));
    b->compteur = 0;
}

void ajout_livre_1(bibliotheque_t b) {
    b.compteur++;
}

bibliotheque_t ajout_livre_2(bibliotheque_t b) {
    b.compteur++;
    return b;
}

void ajout_livre_3(bibliotheque_t* b) {
    b->compteur++;
}

void supprimer_livre(bibliotheque_t *b, size_t index) {
    if (b->livres[index] != NULL) {
        b->compteur--;
        free(b->livres[index]);
        b->livres[index] = NULL;
    }
}

int main() {
    bibliotheque_t bib;
    initialiser_bibliotheque(&bib);
    
    ajout_livre_1(bib);
    // ajout_livre_1 ne peut pas fonctionner car on passe
    // une copie de la structure et non pas la référence
    // mémoire (adresse) à bib.
    
    bib = ajout_livre_2(bib);
    ajout_livre_3(&bib);
    // Avec ajout_livre_3, on passe le pointeur et donc les
    // performances seront meilleures. Pas besoin de faire une
    // copie de 8008 octets.
    
    supprimer_livre(&bib, 0);

    printf("Hello World");
    return 0;
}
