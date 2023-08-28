/*
 * cat-num : affiche ligne par ligne stdin en les numérotant
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Sep-2012 19:26 (eg)
 * Last file update: 14-Sep-2017 10:11 (eg)
 */

/*
Cette première version n’est pas tout à fait correcte:
   - affichage de la ligne 1 même si le fichier est vide
   - afichage d’une ligne supplémentaire en fin de fichier.
*/

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

/*
Note:
    On utilise ici le format "%3d" dans printf. Celui-ci permet 
	d’afficher le numéro de ligne sur 3 caractères cadrés à droite.
	La fonction printf propose de nombreuses options accessibles 
	apès le caractère '%'. Pour avoir la liste de toutes ces 
	options, sous Unix, on peut faire bash $ man 3 printf
*/