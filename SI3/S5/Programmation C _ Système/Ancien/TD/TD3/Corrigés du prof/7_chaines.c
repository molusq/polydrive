/*
 * 7_chaines.c  -- Encore de chaînes
 *
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 30-Sep-2017 21:56
 * Last file update:  3-Oct-2018 15:58 (eg)
 */

#include <stdio.h>
#include <ctype.h>

int Strncmp(const char s1[], const char s2[], int n) {
  int i;

  for (i = 0; i < n && s1[i] != '\0' && s1[i] == s2[i]; i++) {
  }
  if (i == n) return 0;

  return s1[i] - s2[i];
}


int Strcasecmp(const char s1[], const char s2[]) {
  int i;

  for (i = 0; s1[i] != '\0' && tolower(s1[i]) == tolower(s2[i]); i++) {
  }
  return tolower(s1[i]) - tolower(s2[i]);
}


int Strspn(const char s[], const char accept[]) {
  int i, j;

  for (i = 0; s[i] != '\0'; i++) {
    /* chercher si s1[i] apparaît dans accept */
    for (j = 0; accept[j] != '\0'; j++) {
      if (s[i] == accept[j])
        break;
    }
    if (accept[j] == '\0')
      /* on est au bout de accept => s[i] ∉ accept */
      break;
  }
  return i;
}


int main() {
  int comp;

  comp = Strncmp("ABCD", "ABCDEFG", 4);
  printf("strncmp: 'ABCD' et 'ABCDEFG' (4) => %d\n", comp);

  comp = Strncmp("ABCD", "ABCDEFG", 100);
  printf("strncmp: 'ABCD' et 'ABCDEFG' (100) => %d\n", comp);

  comp = Strncmp("XY", "XYZ", 2);
  printf("strncmp: 'XY' et 'XYZ' (2) => %d\n", comp);

  comp = Strncmp("XYA", "XYZ", 2);
  printf("strncmp: 'XYA' et 'XYZ' (2) => %d\n", comp);

  comp = Strncmp("XYA", "XYZ", 10);
  printf("strncmp: 'XYA' et 'XYZ' (10) => %d\n", comp);

  comp = Strncmp("XAA", "XYZ", 2);
  printf("strncmp: 'XAA' et 'XYZ' (2) => %d\n", comp);

  comp = Strncmp("XYZ", "XAA", 2);
  printf("strncmp: 'XYZ' et 'XAA' (2) => %d\n", comp);

  /* ----------------------------------------------------------------------*/
  comp = Strcasecmp("abc", "ABC");
  printf("strcasecmp: 'abc' 'ABC' => %d\n", comp);

  comp = Strcasecmp("xyz", "ABC");
  printf("strcasecmp: 'xyz' 'ABC' => %d\n", comp);

  comp = Strcasecmp("XYZ", "abc");
  printf("strcasecmp: 'XYZ' 'abc' => %d\n", comp);

  /* ----------------------------------------------------------------------*/

  comp = Strspn("abcde", "abcde");
  printf("strspn: 'abcde' 'abcde' => %d\n", comp);

  comp = Strspn("abcde", "edcba");
  printf("strspn: 'abcde' 'edcba' => %d\n", comp);

  comp = Strspn("abcde", "abcxy");
  printf("strspn: 'abcde' 'abcxy' => %d\n", comp);

  comp = Strspn("abcde", "xy");
  printf("strspn: 'abcde' 'xy' => %d\n", comp);

  return 0;
}
