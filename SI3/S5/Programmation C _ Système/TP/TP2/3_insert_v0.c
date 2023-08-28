/*
 * 3_insert.c   -- Insertion dans un tableau
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  9-Oct-2013 22:52 (eg)
 * Last file update: 23-Sep-2016 17:16 (eg)
 */

/*
On veut écrire la fonction

void insertion(int array[], int nbval, int n);

qui insère l’entier n dans le tableau array rempli de nbval valeurs.

Une première version de cet algorithme pourrait être
   - Trouver la position j où doit se faire l’insertion
   - Décaler les éléments à droite de j de 1 case vers la droite
   - Mettre n dans la case j du tableau
*/

#include <stdio.h>

// Insertion de n à sa place dans le tableau array rempli de nbval valeurs
// Si le tableau n'est pas assez grand ... ça plante.
// ---
void insertion(int array[], int nbval, int n)
{
  int i, j;

  // Trouver la position j où doit se faire l'insertion
  for (j = 0; j < nbval; j++) {
    if (array[j] > n) break;  // break sort de la boucle
  }

  // décaler les éléments à droite de j de 1 case vers la droite
  for (i = nbval; i > j; i--) {
    array[i] = array[i-1];
  }

  // Mettre n dans array[j]
  array[j] = n;
}
//---

void print_array(int array[], int nbval){
  int i;

  printf("[ ");
  for (i = 0; i < nbval; i++)
    printf("%d ", array[i]);
  printf("]\n");
}


int main(void)
{
  int array[100];
  int n, items, nbval = 0;

  do {
    printf("Entrer un entier: ");
    items = scanf("%d", &n);
    if (items == 1 && n >= 0) {
      /* le nombre saisi est positif */
      insertion(array, nbval++, n);
      print_array(array, nbval);
    }
  } while (items == 1  && n >= 0);
}
