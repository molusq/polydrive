/*
 * Calcul de la valeur absolue.
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Sep-2012 19:26 (eg)
 * Last file update: 14-Sep-2017 10:01 (eg)
 */
#include <stdio.h>

int main() {
  int n, abs, items;

  /* Saisie */
  printf("Entrer un entier: ");
  items = scanf("%d", &n);

  if (items == 1) {
    /* Calcul (compliqué...) de la valeur absolue */
    if (n < 0)
      abs = -n;
    else
      abs = n;

    /* Affichage */
    printf("|%d| = %d\n", n, abs);
  } else {
    printf("Erreur de lecture (pas un entier ou fin de fichier rencontrée)\n");
  }
  return 0;
}

/*
Note:
    Ici, on regarde le résultat de la fonction scanf pour voir si on a réussi à 
	lire un nombre (en fait cette fonction renvoie le nombre de lectures qui ont 
	réussi; ici comme on a qu’un seul ‘%’, scanf peut renvoyer 0 ou 1 seulement.
*/