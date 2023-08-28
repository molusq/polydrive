//
// main.c       -- le programme principal de test des HashTables
//
//
//           Author: Erick Gallesio [eg@unice.fr]
//    Creation date: 13-Jan-2014 00:03 (eg)
// Last file update:  2-Jan-2016 09:42 (eg)
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "hash.h"

#define SEPARATORS  " \t\n\r.,:;`\"+-_(){}[]<>*&^%$#@!?~/|\\=1234567890"

void trace(const char *key, const void* value) {
  printf("Key = '%s' value = %lu\n", key, (unsigned long) value);
}


int main(void) {
  char line[1000];
  HashTable ht = hash_new(10);

  while (fgets(line, sizeof(line), stdin)) {
    char *token;

    for (token=strtok(line, SEPARATORS); token; token=strtok(NULL, SEPARATORS)) {
      // Chercher la valeur associée au token lu */
      unsigned long *val = (unsigned long*) hash_find_reference(ht,token);

      if (val) {
        // Le mot était présent dans la table. Incrémenter le compteur
        *val += 1;

        // Imprimer la nouvelle valeu (en allant la chercher dans la table!!!)
        fprintf(stderr, "le mot '%s' est déja %lu fois dans la table\n", token,
                (unsigned long) hash_find(ht, token));
      } else {
        hash_add(ht, token, (void*) 1);
        fprintf(stderr, "on ajoute '%s'\n", token);
      }
    }
  }

  hash_print(ht);
  hash_apply(ht, trace);

  // On peut aussi tester en faisant pas mal grossir la table
  //   for (int i = 0; i < 1000; i++) {
  //     char buffer[20];
  //
  //     sprintf(buffer, "key%d", i);
  //     hash_add(ht, buffer, NULL);
  //   }
  //   hash_print(ht);

  // Libération de la table
  hash_free(&ht);
  return 0;
}
