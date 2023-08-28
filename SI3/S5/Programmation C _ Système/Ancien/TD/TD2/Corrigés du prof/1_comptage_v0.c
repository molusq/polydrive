/*  Affichage du nombre d'occurrences de lettres et chiffres lus sur stdin.
 *  Version avec un seul tableau
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
