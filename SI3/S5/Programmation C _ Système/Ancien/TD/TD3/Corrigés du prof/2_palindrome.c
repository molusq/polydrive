/*
 * 1_palindrome.c       -- Fonction Palindrome
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 10-Oct-2017 13:02
 * Last file update: 10-Oct-2017 13:08 (eg)
 */

#include <stdio.h>
#include <string.h>

int palindrome(const char str[]) {
  for (int i = 0, j = strlen(str)-1; i < j; i++, j--) {
    if (str[i] != str[j]) return 0;
  }
  // si on est ici c'est qu'on a pas trouvé de différence ⇒ renvoyer 1
  return 1;
}

#define test(str) printf("palindrome(\"%s\") ⟶ %d\n", str, palindrome(str))

int main(){
  test("ressasser");
  test("kayak");
  test("X");
  test("tests");
}

