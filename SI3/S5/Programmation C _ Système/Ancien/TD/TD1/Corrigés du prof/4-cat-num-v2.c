/*
 * cat-num : affiche ligne par ligne stdin en les numerotant
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Sep-2012 19:26 (eg)
 * Last file update: 14-Sep-2017 10:11 (eg)
 */

// Cette version est CORRECTE: si le fichier d'entrée est vide, rien
// n'est affiché. Principe: on affiche le numéro de ligne quand le
// caractère précédent est un newline

#include <stdio.h>

int main() {
  int c, prec_c;    /* Caractere courant, precedent */
  int no_ligne = 1; /* No de ligne courant */

  prec_c = '\n';        /* permet d'afficher la ligne 1 (si elle existe) */

  while ((c=getchar()) != EOF) {
    if (prec_c == '\n')
      printf("%3d ", no_ligne++);
    putchar(c);
    /* placer le caractère courant dans prec_c */
    prec_c = c;
  }

  return 0;
}
