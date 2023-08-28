/*
 * 4_dump_hexa.c        -- Dump héxadécimal
 */

#include <stdio.h>
#define MAXLIGNE 16

void imprimer_ligne(char ligne[], int lg)
{
  int j;

  // Afichage du code des caractères
  for (j = 0; j < MAXLIGNE; j++) {
    if (j < lg)
      // Impression en hexadécimal . Le cast sert au cas où le char serait signé.
      printf("%02x ", (unsigned char) ligne[j]);
    else
      // Afficher des espaces pour alignement
      printf("   ");
  }

  // Affichage des caractères
  printf(" | ");
  for (j = 0; j < lg; j++) {
    char c = ligne[j];

    printf("%c", (' ' <= c && c <= '~') ? c: '.');
  }
  printf("\n");
}


int main()
{
  int c, i = 0;
  char ligne[MAXLIGNE];

  while ((c = getchar()) != EOF) {
    ligne[i++] = c;
    if (i == MAXLIGNE) {         // le tableau est plein, l'imprimer et le "vider"
      imprimer_ligne(ligne, i);
      i = 0;
    }
  }

  // Quand on est en fin de fichier, on a peut-être des caractères
  // dans le tableau (si la taille du fichier n'est pas un multiple de
  // MAXLIGNE, c'est le cas). Imprimer le morceau de tableau
  if (i != 0) imprimer_ligne(ligne, i);

  return 0;
}
