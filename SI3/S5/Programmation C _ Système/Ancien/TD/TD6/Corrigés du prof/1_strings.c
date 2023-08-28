/*
 * string.c     -- Exercice sur les chaînes de caractères
 * 
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  4-Dec-2013 19:30 (eg)
 * Last file update: 20-Nov-2016 21:54 (eg)
 */

#include <stdio.h>
#include <stdlib.h>

char *Strchr(const char *s, int c) {
  for ( ; *s ; s++)
    if (*s == c) return (char *) s;
  /* Si on est ici c'est que l'on a pas trouve c dans s */
  return NULL;
}

char *Strcpy(char *s1, const char *s2) {
  char *sauvegarde = s1;

  while ((*s1++ = *s2++)) { /* Rien */ }
  return sauvegarde;
}

int Strlen(const char *s) {
  const char *sauvegarde = s;

  while (*s) s++;
  return s - sauvegarde;
}


char *Strdup(const char *s) {
  char *new = malloc(Strlen(s) + 1);  /* ne pas oublier le + 1 pour le '\0' */

  return new ? Strcpy(new, s): NULL;
}


int main()
{
  char tab[256];
  char *p, *q;

  Strcpy(tab, "Une chaîne de caractères....");
  printf("tab comporte %2d caracteres. tab = '%s'\n", Strlen(tab), tab);
  p = Strchr(tab, 'd');
  printf("adresse du caractère 'd': %p ==> '%s'\n", p, p);
  q = Strdup(p);
  printf("adresse de la copie %p ==> '%s'\n", q, q);

  return 0;
}
