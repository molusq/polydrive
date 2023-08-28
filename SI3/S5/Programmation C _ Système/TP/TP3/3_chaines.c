/*
 * 3_chaines.c          Re-définition des fonctions C strcpy, strcmp, strupper
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 25-Sep-2015 09:17 (eg)
 * Last file update: 25-Sep-2019 09:16 (eg)
 */

#include <stdio.h>

/*
Le code de cette fonction est assez semblable au code vu en cours pour la fonction 
strcat, si ce n’est qu’il n’est pas nécessaire ici de trouver la fin de la première 
chaîne (puisqu’on l’écrase sans vergogne).

Une première version de cette fonction pourrait être:
*/
void strcpy1(char s1[], char s2[]) {    // 1ere version
  int i = 0;

  for (i = 0; s2[i] != '\0'; i++)
    s1[i] = s2[i];

  // Ne pas oublier le '\0' final
  s1[i] = '\0';
}

/*
Il est important ici de ne pas oublier de mettre le caractère nul final, une fois 
les caractères de s2 copié dans s1.

Comme on est sûr de recopier au moins un caractère (même si la chaîne est vide, on 
a au moins le caractère nul à copier), on peut utiliser ici un do-while:
*/
void strcpy2(char s1[], char s2[]) {    // 2e version
  int i = 0;

  do {
    s1[i] = s2[i];
    i++; 
  } while (s2[i-1] != '\0');
}

/*
Notes:

    Les boucles do-while sont rarement adaptées (après tout, le cas ou il n’y a rien 
	à faire est souvent un cas normal).

    Pour éviter de faire un test à l’indice i-1 l’arrêt de la boucle, un programmeur 
	C utilisera probablement plutôt l’écriture suivante (sachant que i++ renvoie la 
	valeur de i avant de l’incrémenter):
*/
void strcpy3(char s1[], char s2[]) {    // 3e version
  int i = 0;

  do {
    s1[i] = s2[i];
  } while (s2[i++] != '\0');
}

/*
Attention, ce que l’on veut faire, c’est comparer les chaînes de façon lexicographique
(et non pas leurs longueurs respectives).

Une première écriture pourrait être:
*/
int strcmp1(char s1[], char s2[]) {
  int i;

  // Avancer sur s1 et s2 tant qu'on est pas à la fin et que les caractères
  // de s1 et s2 sont égaux
  for (i = 0; s1[i] != '\0' && s2[i] != '\0' && s1[i] == s2[i]; i++) {
  }

  // Quand on est ici, on a:
  //   - soit fini les deux chaînes en même temps (alors si[i] == s2[i])
  //   - soit une différence sur les caractères courants de s1 et de s2
  //     (peut être la fin d'une des chaînes, mais aussi deux caractères
  //     non nuls mais différents)
  if (s1[i] == s2[i])
    // les chaînes s1 et s2 sont égales
    return 0;
  else if (s1[i] < s2[i])
    // s1 < s2
    return -1;
  else
    // s1 > s2
    return +1;
}

/*
Le test de la boucle peut se simplifier. En effet, il est inutile de vérifier que l’on 
est pas arrivé à la fin de s1 et à la fin de s2. Si on est arrivé à la fin de s1, alors 
en regardant le caractère courant de s2 on peut statuer. Si la chaîne s2 est plus 
courte que la chaîne s1, le test s1[i] == s2[i] échouera. Donc, on peut se contenter 
de:

  for (i = 0; s1[i] != '\0' && s1[i] == s2[i]; i++) {
  }

De même, le test à la sortie de la boucle est aussi un peu compliqué: on sort soit 
quand les deux chaînes sont identiques et on est à la fin, soit quand les caractères 
courants de s1 et de s2 sont différents. Faire la différence de ces caractères suffit 
pour statuer: si les chaînes sont identiques, la différence vaut 0, sinon la différence 
est négative si s1 est inférieure à s2 et positive dans le cas contraire.

Une version de strcmp peut donc être:
*/
int strcmp2(char s1[], char s2[]) {
  int i;
  
  for (i = 0; s1[i] != '\0' && s1[i] == s2[i]; i++) {
  }
  return s1[i] - s2[i];
}

/*
Pour cette fonction, pas de difficulté, si ce n’est qu’il ne faut pas essayer de mettre 
en majuscule un caractère qui n’est pas minuscule (sinon avec le décalage, on obtient 
n’importe quoi)
*/
void strupper(char s[]) {
  int i;

  for (i = 0; s[i] != '\0'; i++) {
    if ('a' <= s[i] && s[i] <= 'z') // Vérifier que l'on a bien une minuscule
      s[i] += 'A' - 'a';
  }
}

int main() {
  char tab[256];
  int comp;

  strcpy1(tab, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
  printf("tab = %s\n", tab);

  strcpy2(tab, "Hello!");
  printf("tab = %s\n", tab);


  comp = strcmp2("ABCD", "ABCD");
  printf("'ABCD' et 'ABCD' => %d\n", comp);

  comp = strcmp2("ABCD", "AB");
  printf("'ABCD' et 'AB' => %d\n", comp);

  comp = strcmp2(tab, "AB");
  printf("'%s' et 'AB' => %d\n", tab, comp);


  strupper(tab);
  printf("upper = %s\n", tab);

  return 0;
}
