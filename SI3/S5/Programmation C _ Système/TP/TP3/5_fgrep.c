/* fgrep.c:     la fonction strstr et une application: un fgrep simplifié
 *              (ce fgrep ne fonctionne pas si le fichier contient des '\0')
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 25-Sep-2015 09:23 (eg)
 * Last file update:  2-Oct-2019 08:28 (eg)
 */

#include <stdio.h>

/*
Le fonction principale de ce programme est la fonction Strstr(a, b) qui permet 
de savoir si la chaîne b est une sous-chaîne de a. Cette fonction peut être 
définie comme:
*/

// Strstr: renvoie 1 si small est une sous-chaîne de big; renvoie 0 sinon
int Strstr(char big[], char small[]) {
  int i, s, b;

  for (i = 0; big[i]; i++) {
    for (s=0, b=i; small[s]; s++, b++) {
      if (small[s] != big[b]) break;
    }
    // Si on est arrivé au bout de small (i.e small[s] == '\0') on a trouvé
    if (small[s] == '\0') return 1;
  }

  // Si on est là, c'est qu'on a pas trouvé small dans big
  return 0;
}

/*
Note:
    Il est important de noter ici que la deuxième boucle ne doit pas avancer 
	l’indice i pendant que l’on recherche si le caractère courant de big est 
	égal au caractère courant de small. En effet, si pendant que l’on avance 
	sur les deux chaînes on s’aperçoit d’une différence quand on est parti de 
	l’indice i de big, on ne pourra pas faire mieux que d’essayer une recherche 
	à l’indice i+1. Pour vous en convaincre essayez de chercher aab dans xaaaby. 
*/

/**************************************************************/

#define MAXLINELEN 1000

/*
Le code pour le programme complet, est en fait assez direct. Il suffit de
   - lire le fichier d’entrée ligne par ligne
   - utiliser Strstr pour voir si la chaîne passée en argument du programme est 
     dans la ligne lue (dans ce cas, la ligne est affichée).

La fonction FastGrep suivante permet de chercher sur le fichier standard d’entrée 
la chaîne qui lui est passée en paramètre.
*/
void FastGrep (char pattern[]) {
  int c;                   // caractere lu depuis stdin (type ENTIER)
  int size = 0;            // taille de la ligne lue, \n non compris
  char line[MAXLINELEN+1]; // buffer contenant une ligne

  while ((c = getchar()) != EOF) {
    if (size < MAXLINELEN) 
      line[size++] = c;   // Les lignes trop longues sont tronquées. Tant-pis

    if (c == '\n') {
      line[size] = '\0';
      if (Strstr(line, pattern)) printf("%s", line);
      size = 0;
    }
  }

  // Quand on sort sur EOF, on peut avoir une ligne (sans '\n') partiellement lue
  if (size > 0) {
    line[size] = '\0';
    if (Strstr(line, pattern)) printf("%s", line);
  }
}

int main (int argc, char *argv[]) {
  if (argc == 2) FastGrep(argv[1]);
  else
    printf("Usage: fgrep chaine\n");
  return 0;
}
