/*
 * 4_ suppress_char.c      -- Suppression d'un caractère dans une chaîne
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 24-Sep-2018 19:45
 * Last file update: 27-Sep-2018 15:01 (eg)
 */

#include <stdio.h>
#include <string.h>

void suppress_char_v0(char str[], char c) {
  int j = 0;
  for (int i = 0; str[i]; i++) {
    if (str[i] != c) {
      str[j] = str[i];
      j += 1;
    }
  }
  str[j] = '\0';
}

// Une version qui évite de remettre  les caractères de str à la même position...
void suppress_char(char str[], char c) {
  int j = 0;
  for (int i = 0; str[i]; i++) {
    if (str[i] != c) {
      if (i != j) str[j] = str[i];
      j += 1;
    }
  }
  str[j] = '\0';
}


void test_supprimer(char str[], char c) {
  char ch[100];

  printf("Suppression de '%c' dans '%s' ⇒ ", c, str);
  strcpy(ch, str);
  suppress_char(ch, c);
  printf("'%s'\n", ch);
}

int main() {
  test_supprimer("Bonjour", 'o');
  test_supprimer("Bonjour", 'x');
  test_supprimer("Bonjour", 'B');
  test_supprimer("Bonjour", 'r');
  test_supprimer("On enlève les expaces de la chaîne", ' ');
  return 0;
}
