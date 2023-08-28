/*
 * entiers.c        -- nombre d'entiers lus
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 13-Sep-2012 00:32 (eg)
 * Last file update: 14-Sep-2017 10:02 (eg)
 */

/*
Pour cet exercice, on va utiliser un boucle do (plutot que while) 
qui est mieux adaptée. En effet, ici on est sûr de devoir rentrer 
au moins une fois dans la boucle.
*/

#include <stdio.h>

int main(void) {
  int n;       /* Entier saisi */
  int cpt = 0; /* Compteur */
  int items;   /* nombre d'éléments lus par scanf */

  do {
    printf("Entrer un entier: ");
    items = scanf("%d", &n);

    if (n > 0 && items == 1)
      /* on a bien saisi un nombre et il est positif */
      cpt += 1;
  } while (n > 0 && items == 1);

  printf("Nombre d'entiers saisis: %d\n", cpt);

  return 0;
}

/*
Remarque:
    Il est assez rare que l’on puisse utiliser la boucle do-while 
	puisque, quand on doit effectuer une séquence d’actions, il est 
	normal de traiter aussi le cas ou cette liste est vide (ce que 
	permet la boucle while). A contrario, la boucle do-while est 
	bien adaptée lorsqu’on interagit avec un utilisateur, puisqu’il 
	faut lui poser une question et recommencer si sa réponse est 
	incorrecte ou si on a pas lu toutes les valeurs qu’il devait 
	entrer.
*/