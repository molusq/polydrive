/*
 * binaire.c    -- Affichage d'un nombre en binaire
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 12-Oct-2015 10:30 (eg)
 * Last file update:  9-Oct-2019 13:28 (eg)
 */

#include <stdio.h>

// On utilise ici deux versions:
//      - une version de la décomposition récursive
//      - une version qui travaille bit à bit (on positionne le bit le plus
//        fort à 1 (cela correspond au nombre 2**max-1) et on travaille en
//        déplaçant ce bit à droite à chaque tour de boucle. L'inconvénient
//        de cette version est que l'on a un résultat qui laisse les '0' de
//        tête (mais cela peut se corriger facilement et est laissé en exercice).


void binaire_bit_a_bit(unsigned int n) {
  // nombre max de bits dans un int: (sizeof(unsigned int) * 8)
  unsigned masque = 1 << ((sizeof(unsigned int) * 8) - 1);  //  2**max-1

  while (masque > 0) {
    printf("%d", (masque & n) != 0);
    masque >>= 1;
  }
}

void binaire_rec(unsigned int n) {
  if (n > 1)
    binaire_rec(n >> 1);       // ⇔ n / 2
  printf("%d", n & 1);         // ou putchar((n & 1) ? '1' : '0')
}


int main(void) {
  unsigned int n;

  for ( ; ; ) {
    printf("Entrer un nombre positif: ");
    if (scanf("%ud", &n) <= 0) break;

    printf("   version récursive: "); binaire_rec(n);       printf("\n");
    printf("   version bit à bit: "); binaire_bit_a_bit(n); printf("\n");
  }
  return 0;
}
