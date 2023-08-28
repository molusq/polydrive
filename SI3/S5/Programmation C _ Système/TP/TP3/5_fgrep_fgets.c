/* fgrep.c:     la fonction strstr et une application: un fgrep simplifié
 *              (ce fgrep ne fonctionne pas si le fichier contient des '\0')
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 25-Sep-2015 09:23 (eg)
 * Last file update:  3-Oct-2018 13:22 (eg)
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
Si on ne veut pas écrire le code de lecture de lignes, on peut utiliser la 
fonction standard fgets. Cette fonction prend 3 paramètres:
   - le tableau qui contiendra la ligne du fichier
   - sa taille
   - le fichier où il faut lire (pour nous, ici, c’est stdin)
Le code serait alors beaucoup plus simple:
*/
void FastGrep(char pattern[]) {
  char line[MAXLINELEN]; // buffer contenant une ligne

  while (fgets(line, MAXLINELEN, stdin)) {
    if (Strstr(line, pattern)) printf("%s", line);
  }
}

int main (int argc, char *argv[]) {
  if (argc == 2) FastGrep2(argv[1]);
  else
    printf("Usage: fgrep chaine\n");
  return 0;
}
