/*
 * 1_indice.c   -- Indice d'un caractère dans une chaîne
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 24-Sep-2018 13:02
 * Last file update: 25-Sep-2018 12:31 (eg)
 */

#include <stdio.h>
#include <string.h>

/*
Cette fonction est simple à écrire: elle consiste à parcourir la chaîne 
de caractères en partant de la gauche (indice 0) et en comparant chaque 
caractère de str avec c. Si str[i] == c on peut renvoyer i. Si on arrive 
après la boucle for, c’est que l’on a pas réussi à sortir par un return 
de la boucle (et donc que l’on a pas trouvé c dans str ⟹ on renvoie -1).
*/

int indice(const char str[], const char c) {
  for(int i = 0; str[i] != '\0'; i ++) {
    if (str[i] == c) return i;
  }
  // Si on est là c'est qu'on a pas trouvé c
  return -1;
}

/*
Dans une première version, on pourrait partir du dernier caractère de la 
chaîne (il est à l’indice strlen(str)-1) et en parcourant la chaîne de 
la droite vers la gauche.
Cette fonction est correcte, mais pas très satisfaisante. En effet, elle 
peut nous amener a parcourir deux fois la chaîne str (une fois de gauche 
à droite pour calculer sa longueur et une fois de droite à gauche si c 
n’est pas dans str).
*/
int indice_droite_v0(const char str[], const char c) {
  for(int i = strlen(str)-1; i >= 0; i --) {
    if (str[i] == c) return i;
  }
  // Si on est là c'est qu'on a pas trouvé c
  return -1;
}

/*
Une meilleure écriture de cette fonction pourrait donc être la version 
ci-dessous.

Dans ce cas, la chaîne est parcourue qu’une seule fois. Si on rencontre c 
dans str, on retient sa position. La dernière valeur enregistrée dans res 
correspond à l’indice le plus à droite où se trouve c. Si on ne trouve 
pas c dans str, on renvoie la valeur initiale de res (c’est-à-dire -1).
*/

int indice_droite(const char str[], const char c) {
  int res = -1;  // a priori on a pas trouvé

  for(int i = 0; str[i] != '\0'; i ++) {
    if (str[i] == c) res = i; 
  }
  return res;
}

int main() {
  int res;

  res = indice("Test", 'T');
  printf("indice(\"%s\", '%c') ==> %d\n", "Test", 'T', res);

  res = indice("Test", 't');
  printf("indice(\"%s\", '%c') ==> %d\n", "Test", 't', res);

  res = indice("Test", 'z');
  printf("indice(\"%s\", '%c') ==> %d\n", "Test", 'z', res);

  res = indice("Tester", 'e');
  printf("indice (\"%s\", '%c') ==> %d\n", "Tester", 'e', res);

  // ==================================================
  res = indice_droite_v0("Test", 'T');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Test", 'T', res);

  res = indice_droite_v0("Test", 't');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Test", 't', res);

  res = indice_droite_v0("Test", 'z');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Test", 'z', res);

  res = indice_droite_v0("Tester", 'e');
  printf("indice_droite(\"%s\", '%c') ==> %d\n", "Tester", 'e', res);

  return 0;
}

