// Version simplifiée de la commande wc qui compte sur stdin

/*
Pas de difficulté ici pour compter les lignes et les caractères. 
Le seul point difficile dans cet exercice consiste à savoir quand 
on a un nouveau mot. Le piège est de penser que l’on a un nouveau 
dès que l’on voit un séparateur. En fait, c’est un peu plus compliqué: 
on a un nouveau mot lorsque le caractère courant n’est pas un s
éparateur et que le caractère précédent est un séparateur. Pour éviter 
des tests trop fastidieux, on écrit donc une fonction

   int separateur(char c);
   
pour voir si c est un séparateur (c’est à dire espace, tabulation ou 
newline). Cette fonction renvoie 1 si c est un séparateur et 0 sinon.
*/

#include <stdio.h>

int separateur(char c) {
  return (c == ' ' || c == '\t' || c == '\n');
}

int main() {
  int lines, words, chars, c;
  int prec = '\n';

  lines = words = chars = 0;

  while((c=getchar())!=EOF) {
    chars += 1;
    if (c == '\n')
      lines += 1;
    if (separateur(prec) && !separateur(c))  // !x veut dire 'NOT x'
      words += 1;
    prec = c;                                // mettre à jour prec
  }
  printf("lines: %d\nwords: %d\nchars: %d\n", lines, words, chars);

  return 0;
}
