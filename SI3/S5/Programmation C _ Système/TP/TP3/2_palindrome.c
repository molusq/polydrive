/*
 * 1_palindrome.c       -- Fonction Palindrome
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 10-Oct-2017 13:02
 * Last file update: 10-Oct-2017 13:08 (eg)
 */

#include <stdio.h>
#include <string.h>

/*
Pour regarder si la chaîne str contient un palindrome, il suffit de placer 
l’indice i au début de la chaîne (c’est-à-dire en 0) et l’indice j à la fin 
(c’est-à-dire en strlen(str)-1). Ensuite, on compare str[i] et str[j], tant 
que les indices ne se sont pas croisés.
*/
int palindrome(const char str[]) {
  for (int i = 0, j = strlen(str)-1; i < j; i++, j--) {
    if (str[i] != str[j]) return 0;
  }
  // si on est ici c'est qu'on a pas trouvé de différence ⇒ renvoyer 1
  return 1;
}

/*
Notes:
    Dès que l’on trouve deux caractères différents, on sait que le résultat 
	sera faux et on sort prématurément de la fonction avec le return 0.
    Comme précédemment, si on arrive à la fin de la boucle for , c’est qu’on 
	a pas réussi à sortir avant (et donc que l’on pas pu trouver que le mot 
	n’était pas un palindrome) ⇒ return 1
*/

#define test(str) printf("palindrome(\"%s\") ⟶ %d\n", str, palindrome(str))

int main(){
  test("ressasser");
  test("kayak");
  test("X");
  test("tests");
}

