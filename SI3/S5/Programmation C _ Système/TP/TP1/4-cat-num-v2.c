/*
 * cat-num : affiche ligne par ligne stdin en les numerotant
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Sep-2012 19:26 (eg)
 * Last file update: 14-Sep-2017 10:11 (eg)
 */

/*
Dans cette version, on va éviter les deux problèmes cités précédemment
(numéro de ligne e trop à la fin et affichage du chiffre 1 même si le 
fichier est vide.

L’idée ici est d’avoir une variable qui contient toujours le caractère 
précédent et d’afficher le numéro de ligne seulement quand celui-ci est 
le caractère '\n'. Pour que le numéro de la première ligne soit affiché, 
il suffit d’initialiser le caractère précédent à un saut de ligne. Dans 
cette version, les numéros de lignes sont donc affichés toujours dans la 
boucle. Si le fichier d’entrée est vide, on entre pas dans la boucle (et 
donc, rien n’est affiché).
*/

#include <stdio.h>

int main() {
  int c, prec_c;    /* Caractere courant, precedent */
  int no_ligne = 1; /* No de ligne courant */

  prec_c = '\n';    /* permet d'afficher la ligne 1 (si elle existe) */

  while ((c=getchar()) != EOF) {
    if (prec_c == '\n')
      printf("%3d ", no_ligne++);
    putchar(c);
    /* placer le caractère courant dans prec_c */
    prec_c = c;
  }

  return 0;
}
