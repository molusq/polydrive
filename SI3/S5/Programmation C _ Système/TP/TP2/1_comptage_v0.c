/*  Affichage du nombre d'occurrences de lettres et chiffres lus sur stdin.
 *  Version avec un seul tableau
 */
 
 /*
Dans cette première version, on ne va pas s’occuper de reconnaître les lettres 
et les chiffres seulement, mais compter toutes les occurrences de tous les 
caractères. Du coup, c’est assez simple:
   - on sait qu’il y a 256 caractères ⟹ on va déclarer le tableau compteurs de 
	 256 cases qui nous permettra d’avoir un compteur chaque caractère possible;
   - En C, comme les caractères sont des (petits) entiers, on trouvera donc le 
     compteur associé au caractère 'X' dans la case compteur['X'].

Bien sûr, toutes les cases du tableau compteurs doivent être initialisées à 0 
avant de commencer à parcourir le fichier d’entrée. Pour cela, on peut faire 
une boucle qui parcourt les 256 case et leur affcte la valeur 0. On peut aussi
 utiliser la notation {} qui permet d’initialiser un tableau d’entiers à 0 (on 
 verra pourquoi un peu plus tard en cours).

Le début de notre programme peut donc être:

  int compteurs[256] = {};
  int c;

  while ((c=getchar())!=EOF)
    compteurs[c] += 1;

Une fois que l’on a lu tout fichier d’entrée, on doit afficher le nombre de 
chiffres et de lettres lues. Pour cela on va faire 3 boucles (une pour les 
chiffres, une pour les minuscules et une pour les majuscules). Cela donne:

 for (c = '0'; c <= '9'; c++)
    if (compteurs[c]!= 0) printf("%c: %2d fois\n", c, compteurs[c]);
  for (c = 'a'; c <= 'z'; c++)
    if (compteurs[c] != 0) printf("%c: %2d fois\n", c, compteurs[c]);
  for (c = 'A'; c <= 'Z'; c++)
    if (compteurs[c] != 0) printf("%c: %2d fois\n", c, compteurs[c]);

Noter que l’on n’affiche pas ici les compteurs nuls pour ne pas afficher des 
informations inutiles.
*/

#include <stdio.h>

int main() {
  int compteurs[256] = {};
  int c;

  //
  // Lecture du fichier
  //
  while ((c=getchar())!=EOF)
    compteurs[c] += 1;

  //
  // Affichage des caractères vus (pas d'affichage si le compteur est à 0)
  //
  for (c = '0'; c <= '9'; c++)
    if (compteurs[c]!= 0) printf("%c: %2d fois\n", c, compteurs[c]);
  for (c = 'a'; c <= 'z'; c++)
    if (compteurs[c] != 0) printf("%c: %2d fois\n", c, compteurs[c]);
  for (c = 'A'; c <= 'Z'; c++)
    if (compteurs[c] != 0) printf("%c: %2d fois\n", c, compteurs[c]);

  return 0;
}
