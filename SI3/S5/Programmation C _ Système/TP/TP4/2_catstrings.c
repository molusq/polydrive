/*
 * catstrings.c       -- Affichage de chaînes en nombre variable
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 14-Oct-2016 11:01 (eg)
 * Last file update: 25-Oct-2021 19:13 (eg)
 */
#include <stdarg.h>
#include <stdio.h>

/*
Cet exercice est très proche du code vu en cours pour calculer le
maximum d’une série d’entiers positifs.

Pour la fonction cat_strings, les objets que l’on doit aller
chercher dans la pile sont des chaînes de caractères.

Pour aller chercher une chaîne, il faut faire va_arg(ap, char*).
Malheureusement, la notation va_arg(ap, char[]) ne marche pas ici
(on verra pourquoi quand on aura vu les pointeurs).

A part cela, pas de difficulté et la fonction peut s’écrire de la
façon suivante:
*/
void cat_strings(char str[], ...) {
  va_list ap;
  va_start(ap, str);

  for (; str != NULL; str = va_arg(ap, char *))
    printf("%s", str);

  va_end(ap);
}

int main() {
  cat_strings("Hello", ", wor", "ld.", "\n", NULL);
  return 0;
}
