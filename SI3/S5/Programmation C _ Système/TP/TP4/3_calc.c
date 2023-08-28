/*                                                      -*- coding: utf-8 -*-
 * calc.c       -- Une calculatrice ultra basique (problème si division par 0)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 30-Oct-2013 13:38 (eg)
 * Last file update: 23-Oct-2017 15:11 (eg)
 */

#include <stdarg.h>
#include <stdio.h>

/*
Pour travailler on initialise ici le résultat (res) à la valeur contenue
dans le premier opérande. On place ensuite l’opérande courant dans la
variable courant et on effectue le calcul.

    res = res θ courant   // où θ ∈ { '+', '-', '*', '/' }

Bien sûr, ce calcul s’effectue tant que courant est positif. Notre version
de la fonction evaluer peut donc s’écrire de la façon suivante:
*/

int evaluer(char op, int operande, ...) {
  va_list ap;
  int res = operande;  // initialisation du résultat avec le premier nombre

  va_start(ap, operande);  // initialisation de ap
  if (res < 0) return 0;   // si aucun opérande on renvoie 0 par convention

  for (int courant = va_arg(ap, int); courant >= 0; courant = va_arg(ap, int)) {
    switch (op) {
      case '+': res += courant; break;
      case '-': res -= courant; break;
      case '*': res *= courant; break;
      case '/': res /= courant; break;  // incorrect si courant == 0
    }
  }

  va_end(ap);
  return res;
}

/*
Remarque:
    Cette version de la fonction n’est pas correcte dans le cas où on fait une division par 0 (le programme se termine en erreur). Il faut donc tester ce cas explicitement.
*/

int main(void) {
  printf("1+2+3=%d\n", evaluer('+', 1, 2, 3, -1));
  printf("10-(2*2*2)-2=%d\n", evaluer('-', 10, evaluer('*', 2, 2, 2, -1), 2, -1));
  printf("12 / (3 - 3)=%d\n", evaluer('/', 12, evaluer('-', 3, 3, -1), -1));
  printf("- sans opérande: %d\n", evaluer('-', -1));
  return 0;
}
