/*
 * options_1.c  -- Un analyseur d'options à la Unix
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  4-Dec-2013 19:30 (eg)
 * Last file update:  9-Nov-2018 08:55 (eg)
 */

#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
  // Analyse des options
  for (argc--,argv++;  argc && argv[0][0]=='-';  argc--,argv++) {
    if (strcmp(*argv, "--") == 0) {                     // Délimiteur spécial '--'
        argc--; argv++;
        break;
    } else if (argv[0][1] == '-') {                     // Option longue
      printf("Option longue: %s\n", *argv);
    } else {                                            // Option courte
      for (char *opt = &argv[0][1]; *opt; opt++) {
        printf("Option courte: -%c\n", *opt);
      }
    }
  }
  
  // Analyse du reste de la ligne de commande (les paramamètres ici)
  for (   ; argc; argc--,argv++)
    printf("Argument: %s\n", *argv);

  return 0;
}
