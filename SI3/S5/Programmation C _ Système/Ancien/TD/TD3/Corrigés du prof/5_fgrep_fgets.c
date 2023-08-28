/* fgrep.c:     la fonction strstr et une application: un fgrep simplifié
 *              (ce fgrep ne fonctionne pas si le fichier contient des '\0')
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 25-Sep-2015 09:23 (eg)
 * Last file update:  3-Oct-2018 13:22 (eg)
 */

#include <stdio.h>

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


/**************************************************************/

#define MAXLINELEN 1000

void FastGrep(char pattern[]) {
  char line[MAXLINELEN]; // buffer contenant une ligne

  while (fgets(line, MAXLINELEN, stdin)) {
    if (Strstr(line, pattern)) printf("%s", line);
  }
}

int main (int argc, char *argv[]) {
  if (argc == 2) FastGrep(argv[1]);
  else
    printf("Usage: fgrep chaine\n");
  return 0;
}
