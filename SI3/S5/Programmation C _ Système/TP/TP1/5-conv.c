/*                                      -*- coding: utf-8 -*-
 *
 *  Table de conversion Celsius/Fahrenheit (par cast puis par appel a rint)
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Sep-2012 19:26 (eg)
 * Last file update: 14-Sep-2017 10:27 (eg)
 */

/*
La solution donnée ici utilise, dans un même programme, les affichages avec 
un cast (une conversion de type) et, ensuite, avec l’appel à la fonction rint.

cast
    Un cast permet de changer le type d’une expression. Par exemple
	
    (int) 3.2     // résultat l'entier 3
	
    permet de considérer la valeur flottante 3.2 comme un entier (ici le résulta sera 3). 

fonction rint
    la fonction rint permet d’arrondir (et non pas tronquer comme le cast) un nombre 
	flottant. Le résultat est lui même un nombre floattant:

    rint(3.2)     // résultat le floattant 3.0

    Noter que pour utiliser rint, il faut faire l’édition de lien avec la bibliothèque 
	mathématique (option -lm). La compilation du fichier peut donc se faire avec:

    $ gcc -Wall -std=c99 -o 5-conv 5-conv.c -lm
*/

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

/*
Note:
    Le premier format utilisé (%4.1f) permet d’afficher le résultat sur 4 caractères 
	dont 1 après la virgule soit: 2 chiffres, un point décimal et 1 chiffre.

    Le deuxième format utilisé est %2.0f car on affiche un flottant (et pas un entier) 
	sur 2 chiffres avec 0 chiffre après la virgule.
*/