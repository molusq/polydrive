/*
 * suppr.c      -- Suppression dans une chaîne
 *
 * Copyright © 2017-2018 Erick Gallesio - I3S-CNRS/Polytech Nice-Sophia <eg@unice.fr>
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  3-Oct-2017 09:04
 * Last file update:  3-Oct-2018 15:47 (eg)
 */
#include <stdio.h>
#include <string.h>

// Decaler vers la gauche de n positions depuis l'indice pos
void decaler_gauche(char str[], int pos, int n) {
  int i;

  for (i = pos; str[i] != '\0'; i++) {
    str[i-n] = str[i];
  }
  str[i-n] = '\0'; // comme dans strcat, ne pas oublier de mettre le '\0' final
}

void suppression(char str[], const char suppr[]) {
  int i, j, k;

  for (i = 0; str[i]; i++) {
    for (j=i, k=0; suppr[k]; j++, k++) {
       if (str[j] != suppr[k]) break;
    }
    // Si on est arrivé au bout de suppr, alors
    //    - suppr[k] == '\0'
    //    - k est la longueur de la chaîne suppr
    if (suppr[k] == '\0') {
      decaler_gauche(str, j , k);
      return;
    }
  }
}

void test_suppression(char ch1[], char ch2[]) {
  char ch[100];

  printf("Suppression de '%s' dans '%s' => ", ch2, ch1);
  strcpy(ch, ch1);
  suppression(ch, ch2);
  printf("'%s'\n", ch);
}

int main() {
  test_suppression("Bonjour", "on");
  test_suppression("Bonjour", "B");
  test_suppression("Bonjour", "Bonjour");
  test_suppression("Bonjour", "jour");
  test_suppression("Bonjour", "abc");
  return 0;
}
