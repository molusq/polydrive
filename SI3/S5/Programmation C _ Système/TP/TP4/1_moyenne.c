/*
 * moyenne.c    -- moyenne de nombres floattants
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 10-Oct-2018 18:04
 * Last file update: 10-Oct-2018 18:10 (eg)
 */

#include <stdarg.h>
#include <stdio.h>

/*
Dans cet exercice, nous savons donc exactement le nombre de flottants
qui on été passés à notre fonction. Une première version de notre
fonction pourrait donc être:

float moyenne(int count, ...) {
  va_list ap;
  float sum = 0;

  va_start(ap, count);                 // initiialiser "ap" après "count"
  for (int i = 0; i < count; i++) {
    sum += va_arg(ap, float);          // ajouter le ieme flottant dans "sum"
  }
  va_end(ap);

  return sum / count;                  // renvoyer la moyene
}

En fait cette version ne marche pas (elle provoque même l’arrêt brutal
du programme). Toutefois, le compilateur nous aide ici avec un warning
indiquant que les nombres flottants sont convertis en double lorsqu’ils
sont passés en paramètre à une fonction (on dit qu’ils sont promus en
doubles). Par conséquent, il faudra indiquer que l’on va chercher un
double dans la pile (plutôt qu’un float) puisque c’est ce qu’a mis le
compilateur dans la pile lors de l’appel.

Par ailleurs, il faut que nous fassions attention quand count est égal
à 0, sous peine de provoquer une division par 0 lors du calcul de la
valeur qui suit le return! Le code correct de la fonction est donc:
*/

float moyenne(int count, ...) {
  va_list ap;
  float sum = 0;

  if (!count) return 0;  // Cas particulier où il n'y a pas de valeur

  va_start(ap, count);
  for (int i = 0; i < count; i++) {
    sum += (float)va_arg(ap, double);
  }
  va_end(ap);
  return sum / count;
}

int main() {
  printf("Moyenne1 = %f\n", moyenne(2, 10.0, 15.0));
  printf("Moyenne2 = %f\n", moyenne(5, 10.0, 15.0, 18.5, 0.0, 3.5));
  printf("Moyenne3 = %f\n", moyenne(0));
  return 0;
}
