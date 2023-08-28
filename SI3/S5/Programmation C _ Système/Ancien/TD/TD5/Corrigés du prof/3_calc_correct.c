/*                                                      -*- coding: utf-8 -*-
 * calc.c       -- Une calculatrice ultra basique ( version corrigée)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 30-Oct-2013 13:38 (eg)
 * Last file update: 23-Oct-2017 15:12 (eg)
 */


#include <stdarg.h>
#include <stdio.h>

int evaluer(char op, int operande, ...) {
  va_list ap;
  int res = operande;      // initialisation du résultat avec le premier nombre

  va_start(ap, operande);  // initialisation de ap
  if (res < 0) return 0;   // si aucun opérande on renvoie 0 par convention

  for(int courant = va_arg(ap, int); courant >= 0; courant = va_arg(ap, int)) {
    switch (op) {
      case '+': res += courant; break;
      case '-': res -= courant; break;
      case '*': res *= courant; break;
      case '/': if (courant != 0) {
                  res /= courant; break;
                } else {
                  printf("*** ERREUR: division par 0\n");
                  va_end(ap);
                  return 0;
                }
    }
  }
  va_end(ap);
  return res;
}


int main(void) {
  printf("1+2+3=%d\n", evaluer('+', 1, 2, 3, -1));
  printf("10-(2*2*2)-2=%d\n", evaluer('-', 10, evaluer('*', 2, 2, 2, -1), 2, -1));
  printf("12 / (3 - 3)=%d\n", evaluer('/', 12, evaluer('-', 3, 3, -1), -1));
  printf("- sans opérande: %d\n", evaluer('-', -1));
  return 0;
}
