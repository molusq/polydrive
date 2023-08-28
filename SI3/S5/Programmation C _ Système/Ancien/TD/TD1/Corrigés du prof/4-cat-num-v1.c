/*
 * cat-num : affiche ligne par ligne stdin en les numérotant
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Sep-2012 19:26 (eg)
 * Last file update: 14-Sep-2017 10:11 (eg)
 */

// CETTE VERSION EST INCORRECTE:
//    Si le fichier d'entrée est vide, le programme affiche tout de même
//    le chiffre 1 (alors qu'il ne devrait rien afficher).

#include <stdio.h>

int main() {
  int c;            /* caractere courant */
  int no_ligne = 1; /* No de ligne courant */

  printf("%3d ",no_ligne++);
  while ((c=getchar())!=EOF) {
    putchar(c);
    if (c == '\n') printf("%3d ",no_ligne++);
  }
  return 0;
}
