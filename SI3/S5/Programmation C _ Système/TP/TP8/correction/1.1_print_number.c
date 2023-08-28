/*
 * print_number.c     -- Impression d'un nombre
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 12-Oct-2017 12:36
 * Last file update: 25-Oct-2017 13:53 (eg)
 */

#include <stdio.h>

// Pour imprimer le nombre 123, il faut imprimer le caractère 1 (càd le caractère
// dont le code est '0'+ 1), suivi de du caractère 2 (c'est à dire '0'+ 2)...
// Pour cet exercice, on peut avoir deux types de solution:
//  - une solution itérative
//  - une solution récursive

// Une première méthode pour imprimer un nombre consiste à trouver les chiffres à
// afficher par des divisions par 10 successives. Ainsi, pour afficher 123, on
// affichera le reste de la division par 10 (⇒ 3) puis on affichera ensuite le
// quotient de 123 par 10 (⇒ 12).

// Le problème avec cette méthode est que l’on obtient les chiffres dans l’ordre
// inverse. On passe donc par un tableau qui permet de stocker les restes de la
// division par 10. Ce tableau sera ensuite imprimé à l’envers.
// Notes:
//  - La version donnée traite les nombres négatifs
//  - On utilise ici un do-while car on aura toujours au moins un chiffre à afficher.

void print_iteratif(int n) {
  int i, tab[50];  // 50 est largement suffisant (le plus
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
  } while (n);  // ⇔ n != 0

  // Affichage des restes (entiers) avec putchar
  for (--i; i >= 0; i--) {
    putchar('0' + tab[i]);
  }
}

// La version récursive permet d’obtenir directement les chiffres dans le bon ordre:
// en effet pour afficher le nombre 123, il faut d’abord afficher 12 avec une fonction
// qui sait afficher des nombres ≥ 10 (la fonction que l’on est en train d’écrire sait
// faire ça!) et ensuite on affiche les unités (ici 3). Comme on le voit, cette solution
// est beaucoup plus simple que la version itérative. Si on ajoute le traitement du
// signe, nous obtenons:
void print_recursif(int n) {
  if (n < 0) {
    putchar('-');
    print_recursif(-n);
  } else {
    if (n >= 10) print_recursif(n / 10);
    putchar('0' + n % 10);
  }
}

// Pour imprimer dans une base donnée, il suffit de passer un paramètre supplémentaire
// pour avoir la base utilisée et de remplacer les divisions par 10 par des divisions
// par la base.
// Le problème qui nous reste à résoudre est comment afficher un digit d lorsque base
// > 10 (parfois on utilise des chiffres et parfois des lettres).
// En fait on a deux cas, qui sont traités ci-dessous en utilisant l'opérateur ternaire:
void print_base(int n, int base) {
  if (n < 0) {
    putchar('-');
    print_base(-n, base);
  } else {
    int d = n % base;
    if (n >= base) print_base(n / base, base);
    putchar(d + ((d < 10) ? '0' : 'A' - 10));
  }
}

int main() {
  int tests[] = {12345, 0, 3, -12345, 1000000, 15, 16, 8};

  for (int i = 0; i < sizeof(tests) / sizeof(int); i++) {
    int n = tests[i];

    printf("Impression de %d\n", n);
    printf("   Itératif => ");
    print_iteratif(n);
    putchar('\n');
    printf("   Récursif => ");
    print_recursif(n);
    putchar('\n');
    printf("   Base 16  => ");
    print_base(n, 16);
    putchar('\n');
    printf("   Base 8   => ");
    print_base(n, 8);
    putchar('\n');
  }
  return 0;
}
