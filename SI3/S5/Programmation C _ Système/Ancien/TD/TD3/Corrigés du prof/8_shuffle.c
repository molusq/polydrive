/*
 * 8_shuffle.c  -- shuffle a string
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 12-Sep-2017 15:11
 * Last file update:  2-Oct-2019 08:39 (eg)
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#define MAX_LINE 100

void shuffle(char str[]) {
  int len = strlen(str);        // longueur de str (utilisé dans le modulo qui suit)

  for (int i = 0; i < len; ++i) {
    int j =  random() % len;    // ramener le nombre dans l'intervalle [0..len[

    if (i != j) {               // échanger str[i] et str[j] (inutile si (i == j)
      char c =  str[i];
      str[i] = str[j];
      str[j] = c;
    }
  }
}

int main() {
  char line[MAX_LINE];

  // Initialiser le générateur de nombres aléatoires
  srandom(time(NULL));

  // Un test rapide
  while (fgets(line, MAX_LINE, stdin)) {
    /*  Enlever le caractère '\n' de la ligne */
    line[strlen(line)-1] = '\0';

    shuffle(line);
    printf("==> %s\n", line);
  }
  return 0;
}
