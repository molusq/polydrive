/*
 * suppr.c      -- Suppression dans une chaîne
 *
 * Copyright © 2017-2018 Erick Gallesio - I3S-CNRS/Polytech Nice-Sophia <eg@unice.fr>
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  3-Oct-2017 09:04
 * Last file update:  3-Oct-2018 15:47 (eg)
 */
#include <stdio.h>
#include <string.h>

/*
Cette fonction est très simple et est donnée ci-dessous:
*/

// Decaler vers la gauche de n positions depuis l'indice pos
void decaler_gauche(char str[], int pos, int n) {
  int i;

  for (i = pos; str[i] != '\0'; i++) {
    str[i-n] = str[i];
  }
  str[i-n] = '\0'; // comme dans strcat, ne pas oublier de mettre le '\0' final
}

/*
Pour cet exercice, on peut s’inspirer de ce qu’on avait fait dans la fonction Strstr. 
Cette fonction trouvait une sous-chaîne dans une chaîne et renvoyait 1 dès qu’elle 
l’avait trouvée.
Pour rappel, le code de cette fonction était:

     1  int Strstr(char big[], char small[]) {
     2    int i, s, b;
     3  
     4    for (i = 0; big[i]; i++) {
     5      for (s=0, b=i; small[s]; s++, b++) {
     6        if (small[s] != big[b]) break;
     7      }
     8      // Si on est arrivé au bout de small (i.e small[s] == '\0') on a trouvé
     9      if (small[s] == '\0') return 1;
    10    }
    11  
    12    // Si on est là, c'est qu'on a pas trouvé small dans big
    13    return 0;
    14  }
Quand on est à la ligne 9, deux cas sont possibles:
   - On est sorti de façon prématurée de la boucle interne parce qu’on avait un caractère 
	 différent sur big et small. Dans ce cas, small[s] n’est pas égal \0;
   - On a atteint la fin de small (small[s] est égal \0). Si tel est le cas, on a trouvé 
     tous les caractères de small dans big.

Revenons au problème de la suppression d’une chaîne suppr dans une chaîne str. Tout d’abord, 
il faut trouver suppr dans la chaîne source et quand on est sûr de l’avoir trouvée, il 
suffit de décaler la fin de la chaîne str de la longueur de suppr vers la gauche.

On a donc le code suivant (on a juste adapté les noms des variables):
*/
void suppression(char str[], const char suppr[]) {
  int i, j, k;

  for (i = 0; str[i]; i++) {
    for (j=i, k=0; suppr[k]; j++, k++) {
       if (str[j] != suppr[k]) break;
    }
    // Si on est arrivé au bout de suppr, alors
    //    - suppr[k] == '\0'
    //    - k est la longueur de la chaîne suppr
    if (suppr[k] == '\0') {
      decaler_gauche(str, j , k);
      return;
    }
  }
}

/*
Note:
    Le return ici permet de ne supprimer que la première occurrence seulement de suppr dans str.
*/

void test_suppression(char ch1[], char ch2[]) {
  char ch[100];

  printf("Suppression de '%s' dans '%s' => ", ch2, ch1);
  strcpy(ch, ch1);
  suppression(ch, ch2);
  printf("'%s'\n", ch);
}

int main() {
  test_suppression("Bonjour", "on");
  test_suppression("Bonjour", "B");
  test_suppression("Bonjour", "Bonjour");
  test_suppression("Bonjour", "jour");
  test_suppression("Bonjour", "abc");
  return 0;
}
