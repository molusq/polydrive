/*
 * options_2.c  -- Un analyseur d'options à la Unix
 * 
 * Cette version n'utilise pas argc (on sait que le dernier élément du
 * tableau argv est égal à NULL)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  4-Dec-2013 19:30 (eg)
 * Last file update:  9-Nov-2018 09:01 (eg)
 */


#include <stdio.h>
#include <string.h>

// Éviter le warrning sur argc
#pragma GCC diagnostic ignored "-Wunused-parameter"  

int main(int argc, char *argv[]) {
  // Analyse des options
  for (argv++; *argv && argv[0][0]=='-'; argv++) {
    if (strcmp(*argv, "--") == 0) {                     // Délimiteur spécial '--'
      argv++;
      break;
    } else if (argv[0][1] == '-') {                     // Option longue
      printf("Option longue: %s\n", *argv);
    } else {                                            // Option courte
      for (char *opt = *argv+1; *opt; opt++) 
        printf("Option courte: -%c\n", *opt);
    }
  }

  /* Analyse du reste de la ligne de commande */// Autre écriture
  for (   ; *argv; argv++)                      // while (*argv)
    printf("Argument: %s\n", *argv);            //   printf("  ....", *argvv++);

  return 0;
}
