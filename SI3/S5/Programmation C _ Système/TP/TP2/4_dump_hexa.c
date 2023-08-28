/*
 * 4_dump_hexa.c        -- Dump héxadécimal
 */

/*
Dans ce programme, on passe par un tableau de caractères de longueur 16. Ce 
tableau permet de conserver les valeurs que l’on a lues et qu’il faudra 
imprimer sous forme d’entiers puis sous forme de caractères (ce tableau est 
affiché dès qu’il est rempli dans la fonction imprimer_ligne).

Noter que pour afficher un élément du tableau
   - sous forme hexadécimale: on utilise le format "%02x" (x pour hexa, 2 
     pour être sur deux caractères et 0 pour indiquer que les valeurs qui 
     n’utilisent qu’un chiffre doivent être précédées d’un espace – ce qui 
     fait vraiment pro!!!)
   - sous forme de caractère: on utilise simplement le format "%c". Par 
     contre, il faut éviter l’affichage de caractères «bizarres» (passage 
	 à la ligne, beep sonore, tabulation, …) qui casseraient le résultat 
	 (voir sujet). Du coup, on utilise ici l’opérateur ternaire dans 
	 l’expression

    (' ' <= c && c <= '~') ? c: '.'

    qui se lit: si c est dans le bon intervalle, l’afficher sinon afficher 
	un point.

Attention, quand on est en fin de fichier, on a peut-être des caractères dans le 
tableau qui n’ont pas été imprimés (si la taille du fichier n’est pas un multiple 
de 16, c’est effectivement le cas). Il faudra donc penser à imprimer le tableau 
quand on sort de la boucle while.
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