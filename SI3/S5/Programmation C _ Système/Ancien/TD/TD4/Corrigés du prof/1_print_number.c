/*
 * 3_print_number.c     -- Impression d'un nombre
 *
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 12-Oct-2017 12:36
 * Last file update: 25-Oct-2017 13:53 (eg)
 */

#include <stdio.h>

void print_iteratif(int n){
  int i, tab[50];            // 50 est largement suffisant (le plus
                             // grand nombre sur 64 bits nécessite 20 digits)

  // Impression du signe éventuel
  if (n < 0) {
    putchar('-');
    n = -n;
  }

  //  Calcul des restes de la division par 10
  i = 0;
  do {
    tab[i++] = n % 10;
    n /= 10;
  }
  while (n);    // ⇔ n != 0

  // Affichage des restes (entiers) avec putchar
  for (--i; i >= 0; i--) {
    putchar('0'+tab[i]);
  }
}


void print_recursif(int n) {
  if (n < 0) {
    putchar('-');
    print_recursif(-n);
  } else {
    if (n >= 10) print_recursif(n / 10);
    putchar('0' + n%10);
  }
}

void print_base(int n, int base) {
  if (n < 0) {
    putchar('-');
    print_base(-n, base);
  } else {
    int d = n %base;
    if (n >= base) print_base(n / base, base);
    putchar(d + ((d < 10)? '0': 'A' - 10));
  }
}


int main() {
  int tests[] = { 12345, 0, 3, -12345, 1000000, 15, 16, 8 };

  for (int i=0; i < sizeof(tests)/sizeof(int); i++) {
    int n = tests[i];

    printf("Impression de %d\n", n);
    printf("   Itératif => "); print_iteratif(n); putchar('\n');
    printf("   Récursif => "); print_recursif(n); putchar('\n');
    printf("   Base 16  => "); print_base(n, 16); putchar('\n');
    printf("   Base 8   => "); print_base(n, 8); putchar('\n');
  }
  return 0;
}
