//
// hash.c       -- Tables de hash
//
//          Author: Erick Gallesio [eg@unice.fr]
//   Creation date: 12-Jan-2014 11:39 (eg)
// Last file update: 17-Dec-2020 08:34 (eg)


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "hash.h"


// Question 1
typedef struct hash_element *HashElement; // Un élément de la table

struct hash_element {
  char *key;
  void *value;
  struct hash_element *next;
};

struct hash_table {
  HashElement *table;
  int size;
  int items;
};


// Question 2
static void *my_malloc(size_t size) {
  void *res = malloc(size);

  if (!res) {
    fprintf(stderr, "my_malloc: impossible d'allouer %ld caractères\n", size);
    exit(1);
  }
  return res;
}

HashTable hash_new(int size) {
  HashTable new = my_malloc(sizeof(struct hash_table));

  new->table = my_malloc(size * sizeof(struct hash_element));
  new->size  = size;
  new->items = 0;

  // Initialisation du tableau de pointeurs
  for (int i = 0; i < size; i++) // inutile si on utilisait calloc plus haut
    new->table[i] = NULL;

  return new;
}


// Question 3 (attention cette fonction doit être statique)
static unsigned int hash(const char *key) {
  unsigned int H = 0;

  for (int i = 0; key[i]; i++) {
    H += (unsigned int) key[i] * (i+1);
  }
  return H;
}



// Question 4
void **hash_find_reference(const HashTable ht, const char *key) {
  int pos = hash(key) % ht->size;

  for (HashElement el = ht->table[pos]; el; el = el->next) {
    if (strcmp(el-> key, key) == 0) return &(el->value);
  }
  return NULL;
}


void *hash_find(const HashTable ht, const char *key) {
  void **res = hash_find_reference(ht, key);

  return res? *res: NULL;
}



// Question 5
void hash_add(HashTable ht, const char *key, const void *value) {
  if (hash_find(ht, key)) {
    fprintf(stderr, "hash_add: %s déja présent dans la table\n", key);
  } else {
    int pos = hash(key) % ht->size;
    HashElement el  = my_malloc(sizeof(struct hash_element));

    el->key        = strdup(key);
    el->value      = (void *) value;
    el->next       = ht->table[pos];
    ht->table[pos] = el;
    ht->items     += 1;
  }
}

// Question 6
void hash_print(const HashTable ht) {
  printf("Hash table de taille %d (%d occupés)\n", ht->size, ht->items);
  for (int i= 0; i < ht->size; i++) {
    HashElement el = ht->table[i];

    if (el) {
      printf("%6d:", i);
      while (el) {
        printf(" '%s'", el->key);
        el = el->next;
      }
      printf("\n");
    }
  }
}

// Question 7
void hash_apply(const HashTable ht, void (*func)(const char *key,
                                                 const void *value)) {
  for (int i = 0; i < ht->size; i++)
    for (HashElement p = ht->table[i]; p ; p = p->next)
      func(p->key, p->value);
}

// Question 8
void hash_free(HashTable *ht){

  for (int i = 0; i < (*ht)->size; i++) {
    HashElement tmp, p = (*ht)->table[i];

    while (p) {
      tmp = p; p = p->next;
      free(tmp->key);  // Libérer les clés (que nous avons allouées par strdup)
                       // Ne pas libérer les valeurs (que nous n'avons pas allouées)
      free(tmp);
    }
  }
  free((*ht)->table);
}
