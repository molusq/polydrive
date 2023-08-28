/*  Affichage du nombre d'occurrences de lettres et chiffres lus sur stdin  */
#include <stdio.h>

int main() {
  int letters[26]  = {};
  int digits[10]   = {};
  int c;

  //
  // Lecture du fichier
  //
  while ((c=getchar())!=EOF)
    if ('0' <= c && c <= '9') digits[c - '0'] += 1;             // chiffre
    else
      if ('a' <= c && c <= 'z') letters[c - 'a'] += 1;          // minuscule 
      else
        if ('A' <= c && c <= 'Z') letters[c - 'A'] += 1;        // majuscule

  //
  // Affichage des caractères vus (pas d'affichage si le compteur est à 0)
  //
  for (c = '0'; c <= '9'; c++)
    if (digits[c -'0']!= 0) printf("%c: %2d fois\n", c, digits[c - '0']);
  for (c = 'a'; c <= 'z'; c++)
    if (letters[c - 'a'] != 0) printf("%c: %2d fois\n", c, letters[c - 'a']);

  return 0;
}
