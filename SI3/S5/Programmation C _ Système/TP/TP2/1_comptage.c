/*  Affichage du nombre d'occurrences de lettres et chiffres lus sur stdin  */

/*
La première version présente deux inconvénients:
   - l’inconvénient majeur de cette version et que l’on met à jour des compteurs 
     qui ne seront jamais affichés (les compteurs pour les caractères qui ne sont 
     ni des lettres, ni des chiffres).
   - un autre problème est que l’on utilise plus de mémoire que nécessaire, 
     puisqu’on pourrait se contenter d’un tableau de 10 cas pour les chiffres et 
	 de deux tableaux de 26 cases (voire un seul, si on se contente de mettre 
	 ensemble majuscules et minuscules).

Ainsi, pour compter les caractères, il va falloir reconnaître les différentes 
classes de caractères. Par exemple, pour savoir si c est une minuscule, cela peut 
se faire en vérifiant que ('a' <= c && c <= 'z').

Il faut ensuite trouver la case du tableau où se trouve le compteur correspondant 
au caractère c rencontré. Pour cela, il faut calculer le décalage de la lettre 
dans l’alphabet. Pour une lettre minuscule, ce décalage est c - 'a'.

Pour les chiffres, c’est la même chose:
   - le test est ('0' <= c && c <= '9')
   - l’indice d’un digit dans le tableau est c - '0'
*/

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

/*
Notes:
   1. Les tableaux letters et digitsont bien leur cases initialisées à 0 (grâce à 
   l’utilisation de l’agrégat {} lors de la déclaration)
   2. Les majuscules sont comptées dans la case de la lettre minuscule correspondante 
   dans le tableau letters.
*/