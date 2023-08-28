/*                                      -*- coding: utf-8 -*-
 *
 *  Table de conversion Celsius/Fahrenheit (par cast puis par appel a rint)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Sep-2012 19:26 (eg)
 * Last file update: 14-Sep-2017 10:27 (eg)
 */

// Pour utiliser rint, il faut faire l'édition de lien avec la
// bibliothèque mathématique (option -lm).
// La compilation se fait donc de la façon suivante:
//      $ gcc -Wall -std=c99 -o conv conv.c -lm

#include <stdio.h>
#include <math.h>

int main() {
  printf("Avec le cast\n\n");
  printf("+-------+-----+\n");
  for (float c = 0.0; c <= 20.0; c += 0.5)
    printf("| %4.1fC | %2dF |\n", c, (int) (9*c)/5+32 );
  printf("+-------+-----+\n");

  printf("Avec le rint\n\n");
  printf("+-------+-----+\n");
  for (float c=0.0; c <= 20.0; c += 0.5)
    printf("| %4.1fC | %2.0fF |\n", c, rint((9*c)/5+32) );
  printf("+-------+-----+\n");
  
  return 0;
}
