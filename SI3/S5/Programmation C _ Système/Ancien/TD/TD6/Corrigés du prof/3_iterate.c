/*
 * iterate.c    -- Fonction Iterate
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  5-Dec-2012 18:38 (eg)
 * Last file update: 20-Nov-2019 11:42 (eg)
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define ARRAY_SIZE 10


int iterate(int f(int, int), int tab[], int size) {
  int result = tab[0];

  for (int i = 1; i < size; i++) // 1 car result contient déjà tab[0]
    result = f(result, tab[i]);

  return result;
}

/* La même mais récursive ... */
int recursive_iter(int f(int, int), int tab[], int size) {
  if (size == 1)
    return tab[0];
  else
    return f(recursive_iter(f, tab, size-1), tab[size-1]);
}



/****
 ****  Affichage du tableau
 ****/

void display_array(char message[], int tab[], int size) {
  int i;

  printf("%s {", message);
  for (i = 0; i < size; i++)
    printf("%d%s", tab[i], (i < size-1)? ", ":"");
  printf("}\n");
}


/****
 **** Deux fonctions pour tester
 ****/
int somme(int x, int y) { return x + y; }
int max(int x, int y)  { return (x > y) ? x : y; }


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

  display_array("tableau initial:", a, ARRAY_SIZE);

  printf("Somme   = %d\n", iterate(somme, a, ARRAY_SIZE));
  printf("Maximum = %d\n", iterate(max, a, ARRAY_SIZE));

  printf("Somme (rec)   = %d\n", recursive_iter(somme, a, ARRAY_SIZE));
  printf("Maximum (rec) = %d\n", recursive_iter(max, a, ARRAY_SIZE));

  return 0;
}
