/*
 * vprintf.c: codage d'un vprintf simplifié:
 *           affiche la partie verbatim de son format et %% %c %s %d %x
 *           réécriture de Printf avec vprintf et une fonction de debug
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 25-Oct-2021 19:49 (eg)
 * Last file update: 25-Oct-2021 19:56 (eg)
 */

#include <stdarg.h>
#include <stdio.h>

#define BUFF_SIZE 40  // Taille max de la représentation externe d'un
                      // float (aucun contôle ici)

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

void print_string(char str[]) {  // équivalent à fputs(current, stdout)
  for (int i = 0; str[i]; i++)
    putchar(str[i]);
}

void Vprintf(char format[], va_list ap) {
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
        default:          // ---- On a %X où X ∉ {%, s, c, x, d, f}.
          putchar('%');   //         afficher le '%'
          if (format[i])  //         et le car. qui le suit (si il existe)
            putchar(format[i]);
          break;
      }
    }
  }
}

void Printf(char *format, ...) {
  va_list ap;

  va_start(ap, format);
  Vprintf(format, ap);
  va_end(ap);
}

void Debug(char *format, ...) {
  va_list ap;

  va_start(ap, format);
  Printf("***DEBUG: ");
  Vprintf(format, ap);
  putchar('\n');
  va_end(ap);
}

int main() {
  Debug("Utilisation de la fonction de debug");
  Debug("On peut utiliser les caractères %% de Printf");
  Debug("comme on peut le voir ici: 0x%x et %d et un négatif %d",
        161, 123, -12);
  Debug("");
  Debug("et pour finir une %s", "chaîne");
  return 0;
}
