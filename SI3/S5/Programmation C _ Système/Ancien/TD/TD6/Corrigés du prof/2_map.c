/*
 * map.c        -- Fonction Map
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  5-Dec-2012 18:38 (eg)
 * Last file update: 25-Oct-2017 14:35 (eg)
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


#define ARRAY_SIZE 10


/****
 ****  MAP
 ****      [x0,x1,...,xn] -> [fct(x0),fct(x1),...,fct(xn)]
 ****/
void map(int fct(int), int tab[], int size) { // Appliquer fct à chaque élément du tableau
  for (int i = 0; i < size; i++)
    tab[i] = fct(tab[i]);
}

/****
 ****  Affichage du tableau
 ****/

void display_array(char message[], int tab[], int size) {
  int i;

  printf("%s ", message);
  for (i = 0; i < size; i++)
    printf("%3d ", tab[i]);
  printf("\n");
}


/****
 **** Deux fonctions pour tester
 ****/
int carre(int x) { return x * x; }
int succ(int x)  { return x + 1; }

/****
 **** Fonction d'affichage pour voir que map est bien une fonction puissante.
 **** En fait display_array aurait pu être écrite en appelant map.
 ****/
int pr(int x) { printf("%3d ", x); return x;};


/****
 **** main
 ****/
int main(void) {
  int a[ARRAY_SIZE];
  int i;

  /* Initialisation du tableau */
  srand(time(NULL));                    /* init génerateur aléatoire */
  for (i = 0; i < ARRAY_SIZE; i++)
    a[i] = rand() % 20;

  display_array("  tableau initial: ", a, ARRAY_SIZE);
  map(carre, a, ARRAY_SIZE);
  display_array("  carrés:          ", a, ARRAY_SIZE);
  map(succ, a, ARRAY_SIZE);
  display_array("  successeurs:     ", a, ARRAY_SIZE);

  printf("Affichage avec map: "); map(pr, a, ARRAY_SIZE); printf("\n");
  return 0;
}
