/*
 * super_echo.c         -- Un echo avec des options
 *
  *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 15-Oct-2018 16:22
 * Last file update:  4-Nov-2019 19:50 (eg)
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>


void Usage(char *prog) {
  fprintf(stderr, "Usage: %s [options] param1 param2 ...\n", prog);
  fprintf(stderr, "Possible options\n");
  fprintf(stderr, "  -r           reverse each parameter\n");
  fprintf(stderr, "  -O           one parameter by line\n");
  fprintf(stderr, "  -h | --help  display this help and exit\n");
  exit(1);
}

void print_reverse(char *str) {
  for (int i = strlen(str)-1; i >= 0; i--) putchar(str[i]);
}

typedef struct {
  char sep;         // valeur du spérateur ' ' par défaut et '\n' si option -O1
  int reverse;      // 1 si option -r et 0 sinon.
} options;


char **option_analysis(char **argv, options *opt) {
  char *progname = *argv;

  // Parse arguments
  for (++argv;  *argv && argv[0][0]=='-'; argv++) {
    if (strcmp(*argv, "--") == 0) {                     // Special delim  '--'
      argv++;
      break;
    } else if (argv[0][1] == '-') {                     // Long option
      if (strcmp(*argv, "--help") == 0) {
        Usage(progname);
      } else
        fprintf(stderr, "%s: bad long option %s\n", progname, *argv);
    } else {                                            // Short option
      for (char *str = &argv[0][1]; *str; str++)
        switch(*str) {
          case 'r': opt->reverse = 1; break;
          case 'O': opt->sep = '\n';  break;
          case 'h': Usage(progname);  break;
          default:  fprintf(stderr, "%s: bad option -%c\n", progname, *str);
        }
    }
  }
  return argv;
}


// Éviter le warrning sur argc
#pragma GCC diagnostic ignored "-Wunused-parameter"  

int main(int argc, char *argv[]) {
  options opt = { .sep = ' ', .reverse = 0}; // initialize default values

  for (argv = option_analysis(argv, &opt); *argv; argv++) {
    // print string
    if (opt.reverse)
      print_reverse(*argv);
    else
      printf("%s", *argv);
    // print separator
    if (*(argv+1)) putchar(opt.sep);
  }
  putchar('\n');
  return 0;
}
