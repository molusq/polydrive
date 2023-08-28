/*
 * 4_ suppress_char.c      -- Suppression d'un caractère dans une chaîne
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 24-Sep-2018 19:45
 * Last file update: 27-Sep-2018 15:01 (eg)
 */

#include <stdio.h>
#include <string.h>

/*
Pour supprimer un caractère dans un chaîne de caractère, nous allons 
gérer deux indices:
   - l’indice i, parcourt la chaîne str de la gauche vers la droite
   - l’indice j, contient la position ou l’on doit mettre le caractère 
     courant (càd str[i]) dans la chaîne.

Le code de cette fonction pourrait donc être:
*/
void suppress_char_v0(char str[], char c) {
  int j = 0;
  for (int i = 0; str[i]; i++) {
    if (str[i] != c) {
      str[j] = str[i];
      j += 1;
    }
  }
  str[j] = '\0';
}

/*
Dans cette version, chaque caractère de str (différent de c, bien sûr) est 
recopié dans la chaîne elle même. Cela veut dire que si on essaie de supprimer 
un caractère qui n’est pas dans la chaîne, on va réaffecter tous les caractères 
de str à la même position (pas top!). Un (petite) optimisation, consiste à ne 
réaffecter les caractères, et donc décaler, que lorsque cela est nécessaire 
(càd dès que j devient différent de i)
*/
void suppress_char(char str[], char c) {
  int j = 0;
  for (int i = 0; str[i]; i++) {
    if (str[i] != c) {
      if (i != j) str[j] = str[i];
      j += 1;
    }
  }
  str[j] = '\0';
}

void test_supprimer(char str[], char c) {
  char ch[100];

  printf("Suppression de '%c' dans '%s' ⇒ ", c, str);
  strcpy(ch, str);
  suppress_char(ch, c);
  printf("'%s'\n", ch);
}

int main() {
  test_supprimer("Bonjour", 'o');
  test_supprimer("Bonjour", 'x');
  test_supprimer("Bonjour", 'B');
  test_supprimer("Bonjour", 'r');
  test_supprimer("On enlève les expaces de la chaîne", ' ');
  return 0;
}
