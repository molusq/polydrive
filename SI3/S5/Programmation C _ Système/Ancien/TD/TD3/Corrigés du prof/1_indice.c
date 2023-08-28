/*
 * 1_indice.c   -- Indice d'un caractère dans une chaîne
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 24-Sep-2018 13:02
 * Last file update: 25-Sep-2018 12:31 (eg)
 */

#include <stdio.h>
#include <string.h>

int indice(const char str[], const char c) {
  for(int i = 0; str[i] != '\0'; i ++) {
    if (str[i] == c) return i;
  }
  // Si on est là c'est qu'on a pas trouvé c
  return -1;
}

int indice_droite_v0(const char str[], const char c) {
  for(int i = strlen(str)-1; i >= 0; i --) {
    if (str[i] == c) return i;
  }
  // Si on est là c'est qu'on a pas trouvé c
  return -1;
}


int indice_droite(const char str[], const char c) {
  int res = -1;  // a priori on a pas trouvé

  for(int i = 0; str[i] != '\0'; i ++) {
    if (str[i] == c) res = i; 
  }
  return res;
}

int main() {
  int res;

  res = indice("Test", 'T');
  printf("indice(\"%s\", '%c') ==> %d\n", "Test", 'T', res);

  res = indice("Test", 't');
  printf("indice(\"%s\", '%c') ==> %d\n", "Test", 't', res);

  res = indice("Test", 'z');
  printf("indice(\"%s\", '%c') ==> %d\n", "Test", 'z', res);

  res = indice("Tester", 'e');
  printf("indice (\"%s\", '%c') ==> %d\n", "Tester", 'e', res);

  

  // ==================================================
  res = indice_droite_v0("Test", 'T');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Test", 'T', res);

  res = indice_droite_v0("Test", 't');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Test", 't', res);

  res = indice_droite_v0("Test", 'z');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Test", 'z', res);

  res = indice_droite_v0("Tester", 'e');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Tester", 'e', res);

  return 0;
}

