/*
 * 3_insert.c   -- Insertion dans un tableau
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  9-Oct-2013 22:52 (eg)
 * Last file update: 22-Sep-2021 11:36 (eg)
 */

/*
En fait, on peut faire bien mieux en combinant les deux premières boucles. 
Puisque de toutes les façons on va insérer notre nouveau nombre n, on peut 
commencer par décaler systématiquement d’un élément vers la droite, tant 
que cela est nécessaire. On obtient un algorithme, plus beau (mais moins 
évident à trouver...).
*/

#include <stdio.h>

// Insertion de n à sa place dans le tableau array rempli de nbval valeurs
// Si le tableau n'est pas assez grand ... ça plante
//---
void insertion(int array[], int nbval, int n)
{
  int j;

  for (j = nbval; (j > 0) && (array[j-1] > n); j--) { // Décalage des éléments > à n
    array[j] = array[j-1];
  }
  array[j] = n;                                        // Insertion
}
// ---

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
    // Sortir quand:
    //    - items != 1 (on a pas réussi à lire un nombre erreur ou fin de fichier (on a tapé ^D) )
    //    - le tabeau est plein (nbval == 100)
  } while (items == 1  && nbval < 100);
}
