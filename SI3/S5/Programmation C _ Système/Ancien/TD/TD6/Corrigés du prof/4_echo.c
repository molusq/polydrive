/*
 * echo.c       -- A simple echo with only one option: -r (print reverse)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  5-Nov-2018 14:40 (eg)
 * Last file update:  9-Nov-2018 09:33 (eg)
 */

#include <stdio.h>
#include <string.h>

void print_reverse(char *str) {
  for (int i = strlen(str); i >= 0; i--) putchar(str[i]);
}

int main(int argc, char *argv[]) {
  int reverse = 0;

  if (argc > 1 && strcmp(argv[1], "-r") == 0) {
    argc -= 1;
    argv += 1;
    reverse = 1;        // retain that we have seen the reverse option
  }

  for (int i = 1; i < argc; i++) {
    // print parameter
    if (reverse)
      print_reverse(argv[i]);
    else
      printf("%s", argv[i]);

    // print a space (if not the last parameter)
    if (i != argc-1) putchar(' ');
  }
  putchar('\n');

  return 0;
}
