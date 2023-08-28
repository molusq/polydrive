/*
 * printf.c: codage d'un printf simplifié:
 *           affiche la partie verbatim de son format et %% %c %s %d %x
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: A long long time ago
 * Last file update:  5-Nov-2021 13:36 (eg)
 */

#include <stdarg.h>
#include <stdio.h>

#define BUFF_SIZE 40  // Taille max de la représentation externe d'un
                      // float (aucun contôle ici)

/*
Pour imprimer dans une base donnée, il suffit de passer un paramètre supplémentaire pour avoir la base utilisée et de remplacer les divisions par 10 par des divisions par la base.

Le problème qui nous reste à résoudre est comment afficher un digit d lorsque base > 10 (parfois on utilise des chiffres et parfois des lettres).

En fait on a deux cas, qui sont traités ci-dessous:

  if (d < 10)
    putchar('0' + d);            // affichage d'un chiffre
  else
    putchar('A' + d - 10);       // d ≥ 10 ⇒ affichage d'une lettre

En utilisant l’opérateur ternaire de C, ce test peut s’écrire:

  putchar(d + ((d < 10)? '0': 'A' - 10))

Ainsi, la version récursive en base quelconque devient:
*/
void print_base(int n, int base) {  // Exo 3 feuille 3
  if (n < 0) {
    putchar('-');
    print_base(-n, base);
  } else {
    int d = n % base;
    if (n >= base) print_base(n / base, base);
    putchar(d + ((d < 10) ? '0' : 'A' - 10));
  }
}

/*
la fonction print_string se résume à
*/
void print_string(char str[]) {  // équivalent à fputs(current, stdout)
  for (int i = 0; str[i]; i++)
    putchar(str[i]);
}

/*
Le code de la fonction Printf consiste à afficher le format caractère par caractère. Deux cas sont possibles:

    le caractère courant n’est pas ‘%’ ⟹ on l’affiche avec un putchar
    le caractère courant est le caractère ‘%’ ⟹ on sait qu’il va falloir aller chercher un paramètre (c’est-à-dire appeler la macro va_arg). Il reste par contre à déterminer le type du paramètre à aller chercher. Ce type est déterminé par le caractère qui suit le ‘%’ (chaîne si ce caractère est ‘s’, entier si le caractère est ‘d’ ou ’x, …)

Le code de la fonction est donc:
*/
void Printf(char format[], ...) {
  va_list ap;

  va_start(ap, format);

  for (int i = 0; format[i]; i++) {
    if (format[i] != '%')
      putchar(format[i]);
    else {
      switch (format[++i]) {
        case 'd':  // ---- Entier en décimal
          print_base(va_arg(ap, int), 10);
          break;
        case 'x':  // ---- Entier en héxadécimal
          print_base(va_arg(ap, int), 16);
          break;
        case 'f': {                // ---- Flottant
          char buffer[BUFF_SIZE];  // DOUBLE et pas FLOAT (lire le warning!)
          snprintf(buffer, BUFF_SIZE, "%f", va_arg(ap, double));
          print_string(buffer);
          break;
        }
        case 's':  // ---- Chaîne de caractères
          print_string(va_arg(ap, char *));
          break;
        case 'c':                    // ---- Caractère
          putchar(va_arg(ap, int));  //  INT, pas CHAR (car promotion entière)
          break;
        case '%':  // ---- Un bête '%' à afficher...
          putchar('%');
          break;
        default:            // ---- On a %X où X ∉ {%, s, c, x, d, f}.
          putchar('%');     //         afficher le '%'
          if (format[i]) {  //         et le car. qui le suit (si il existe)
            putchar(format[i]);
            break;
          } else {
            va_end(ap);  //         un '%' seul à la fin de la chaîne
            return;
          }
      }
    }
  }
  va_end(ap);
}

int main() {
  Printf("DEBUT\n%s%c c'est moi.\nTest nombres: 0x%x et %d et un négatif %d\n",
         "salut", ',', 161, 123, -12);
  Printf("Trois nombres sur la même ligne: %d %f %d\n", 1, 2.0, 3);
  Printf("Affichage d'un '%c' et encore un d'une autre façon '%%'\n", '%');
  Printf("Affichage d'un %% non suivi d'un caractère spécial ==> %z...\n");
  Printf("Attention au %% en fin de la chaîne format ==> %");
  Printf("\nFIN\n");

  return 0;
}
