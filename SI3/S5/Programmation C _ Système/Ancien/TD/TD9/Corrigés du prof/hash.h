//                                                      -*- coding: utf-8 -*-
//
// hash.h       -- Implementation des tables de hachage
//

#ifndef _HASH_H_
#  define _HASH_H_

typedef struct hash_table   *HashTable;   // La table de hachage

// Allocation d'une nouvelle table de hachage vide.
// La valeur donnée en paramètre indique la taille de la table à sa
// création.
HashTable hash_new(int size);

// Chercher la valeur associée à la clé "key" dans "ht".  Si "key" est
// présent dans la table, cette fonction renvoie la valeur associée à
// cette clé. Dans le cas contraire, cette fonction renvoie la valeur
// NULL
void *hash_find(const HashTable ht, const char *key);


// Chercher la valeur associée à la clé "key" dans "ht".  Si "key" est
// présent dans la table, cette fonction renvoie un pointeur sur la
// valeur associée à cette clé. Dans le cas contraire, cette fonction
// renvoie la valeur NULL
void **hash_find_reference(const HashTable ht, const char *key);


// Ajout de la valeur "value" associée à la clé "key" dans "ht".  Si
// "key" est déjà présent on affiche un message d'erreur et on
// sort. Sinon, une nouvelle entrée est créée pour "key".
void hash_add(HashTable ht, const char *key, const void *value);


// Impression de la table "ht" sur le fichier standard de sortie. Pour
// chaque élément, on affiche que sa clé et l'indice où il est rangé
// dans la table. Un exemple d'exécution est donné ci-dessous:
//
//         Hash table de taille 10
//              0:  'dix'
//              1:  'neuf' 'cinq'
//              2:  'trois'
//              3:  'deux'
//              5:  'six'
//              7:  'huit' 'sept' 'un'
//              8:  'quatre'
//
// Ici, on voit que la case d'indice 4 n'est pas utilisée alors que
// celle d'indice 1 comporte deux clés: "neuf" et "cinq".
void hash_print(const HashTable ht);


// Appliquer la fonction utilisateur "func" à tous les éléments de la table "ht"
void hash_apply(const HashTable ht, void (*func)(const char *key, const void *value));


// Libèrer la hash table. Attention le contenu de la table lui même
// n'est pas libéré.  C'est à l'utilisateur de le faire
void hash_free(HashTable *ht);

#endif //_HASH_H_
