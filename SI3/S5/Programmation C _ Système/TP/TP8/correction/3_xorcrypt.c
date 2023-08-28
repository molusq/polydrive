/*
 * xorcrypt.c      -- Xor crypting
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  9-Jan-2017 11:17 (eg)
 * Last file update: 18-Oct-2021 20:10 (eg)
 */

#include <stdio.h>
#include <string.h>

void xorcrypt(char message[], char key[]) {
  for (int i = 0, k = 0; message[i]; i++, k++) {
    if (!key[k]) k = 0;
    message[i] ^= key[k];
  }
}

int main() {
  char s[] = "HELLO, WORLD";

  printf("Avant chiffrement: %s\n", s);
  xorcrypt(s, "abcde");
  printf("Après chiffrement: %s\n", s);
  xorcrypt(s, "abcde");
  printf("Chaîne originale   %s\n", s);
  return 0;
}
