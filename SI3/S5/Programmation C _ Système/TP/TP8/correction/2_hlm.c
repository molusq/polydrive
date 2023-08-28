/*                                                      -*- coding: utf-8 -*-
 * hlm.c        -- HLM: les grand ensembles
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 22-Oct-2012 20:01 (eg)
 * Last file update: 15-Oct-2019 17:49 (eg)
 */

#include <stdio.h>

// Pour cet exercice, la seule vraie difficulté est de trouver l’endroit où
// se trouve le bit qui nous intéresse (pour le tester ou pour le mettre à 1).
// En fait, on peut trouver ce bit avec le quotient et le reste de la division
// par 8.
// Exemple:
//  Supposons que l’on veuille ajouter 12 à un ensemble E. Pour cela, il faut
//  d’abord trouver la case du tableau où se trouve le bit correspondant à
//  l’entier 12. La division entière 12/8 (résultat 1), nous indique que 12
//  est dans la case d’indice 1 du tableau. Le reste de la division (12%8 ⇒ 4)
//  nous donne la position du bit dans cet octet. Nous devons donc mettre à 1
//  le bit n° 4 du caractère situé dans la case 1 du tableau représentant
//  l’ensemble.
//
//  Si la case d’indice 1 contenait déjà la valeur 00100100, pour mettre le bit
//  n° 4 à 1, il suffit d’appliquer un OR (bit à bit) avec la valeur 1<<4 (c’est
//  à dire 00010000). En effet nous avons:
//       00100100
//    OR 00010000
//       --------
//     = 00110100

//  De la même façon, pour tester si la valeur 12 est dans l’ensemble E, il
//  suffit de tester si le 4ème bit de E[1] est à 1. Pour cela, l’opération
//  que nous devons utiliser est un AND (bit à bit) avec la valeur 1<<4. En
//  effet, nous avons:
//        001X0100
//    AND 00010000
//        --------
//      = 000X0000
//
//  Ici, si X est égal à 0, la valeur du AND sera égale à 0 (qui, on le
//  rappelle, veut dire faux en C) et si X est égal à 1, le résultat est 1<<4,
//  qui est différent de 0 (et donc vrai).
//
// Donc pour résumer:
//  - ajouter l’élément 12 dans l’ensemble E: E[1] = E[1] | (1<<4);
//  - tester si l’élément 12 est dans l’ensemble: if (E[1] & (1<<4)) ... else ...

#define CHAR_SIZE 8    /* nombre de bits dans un char */
#define MAX_BIGSET 125 /* nombre de cellules dans un ens */
#define MAX_VAL (CHAR_SIZE * MAX_BIGSET)

typedef unsigned char BigSet[MAX_BIGSET];

void BigSet_init(BigSet bs) {
  for (int i = 0; i < MAX_BIGSET; i++)
    bs[i] = 0;
}

// De la même façon si on veut tester si l’entier i est présent dans l’ensemble
// bs, nous devons:
//  1. calculer la position du bit qui nous intéresse avec le quotient et le reste
//     de la division entière de i par CHAR_SIZE (comme précédemment)
//  2. tester si le bit num est à 1 dans la case bs[slot]. Pour ce test, nous allons
//     ici utiliser un AND (bit à bit).
//     Si la la case bs[slot] était égale à 00100100 et que num est égal à 4, nous avons:
//          00100100
//     AND  00010000
//          --------
//        = 00000000
//     Ceci indique que le bit n° 4 n’est pas égal à 1 dans l’élément slot du tableau.
//     Bien sûr si ce bit était présent, le AND précédent serait égal à 00010000
//     (càd 1<<numm).

// La fonction demandée peut donc être implémentée de la façon suivante.
// Remarque:
//  Cette fonction renvoie 0 si l’élément n’est pas dans l’ensemble et une valeur
//  différente de 0 sinon. Si on voulait renvoyer 0 et 1 seulement, on aurait pu écrire:
//    return (bs[slot] & (1 << num))) != 0;
//
//  En général, le return donné dans la fonction précédente suffit puisque, en C,
//  les tests sont vrais si la valeur testée est différente de 0. Ainsi, pour tester,
//  si i est dans bs, on écrira:
//    if (BigSet_is_in(i, bs)) {    // ⟺ le resultat de BigSet_is_in est ≠ 0
//      ...
//    }

int BigSet_is_in(BigSet bs, int i) {
  int slot = i / CHAR_SIZE;
  int num = i % CHAR_SIZE;

  return bs[slot] & (1 << num);
}

// L’écriture de l’ajout d’un élément dans l’ensemble est donc:
void BigSet_add(BigSet bs, int i) {
  int slot = i / CHAR_SIZE;
  int num = i % CHAR_SIZE;

  bs[slot] |= 1 << num;
}

void BigSet_print(BigSet bs) {
  int virgule = 0;

  // Pour debugger: cela peut être pas mal....
  //   for (i = 0; i < MAX_BIGSET; i++)
  //     printf("%08x ", bs[i]);

  printf("{");
  for (int i = 0; i < MAX_VAL; i++) {
    if (BigSet_is_in(bs, i))
      printf("%s%d", (virgule++) ? ", " : "", i);
  }
  printf("}\n");
}

// Pour construire l’intersection de deux ensembles nous pourrions avoir l’écriture
// ("naturelle") suivante:
// void BigSet_inter(BigSet s1, BigSet s2, BigSet res) {
//   BigSet_init(res);                                  // forcer init de res = ∅
//   for (int i = 0; i < MAX_VAL; i++)
//     if (BigSet_is_in(s1, i) && BigSet_is_in(s2, i))  // si (i ∈ s1) ∧ (i ∈ s2)
//       BigSet_add(res, i);                            //   res = res ∪ {i}
// }

// Cette boucle s’exécute MAX_VAL fois et fait un test asez compliqué à chaque fois
// (noter au passage que l’on calcule 3 fois la même position du bit à chaque tour
// de boucle).

// Une version plus efficace pour calculer l’intersection de deux ensembles consiste
// à utiliser un and bit à bit sur des «paquets» de 8 valeurs. Ce sera bien plus
// efficace que de travailler sur les entiers un par un (puisqu’ici, en quelque sorte,
// on fait un test d’appartenance de 8 valeurs en une seule fois). Par ailleurs, cette
// boucle n’aura qu’à s’exécuter MAX_SET fois. Le code de l’intersection est donc:
void BigSet_inter(BigSet s1, BigSet s2, BigSet res) {
  BigSet_init(res);

  for (int i = 0; i < MAX_BIGSET; i++)
    res[i] = s1[i] & s2[i];
}

int main(void) {
  BigSet e1, e2, e3;

  BigSet_init(e1);
  BigSet_init(e2);

  for (int i = 0; i < 140; i += 12) BigSet_add(e2, i);
  for (int i = 0; i < 140; i += 9) BigSet_add(e1, i);

  BigSet_inter(e1, e2, e3);
  printf("e1 = ");
  BigSet_print(e1);
  printf("e2 = ");
  BigSet_print(e2);
  printf("e3 = ");
  BigSet_print(e3);

  return 0;
}
