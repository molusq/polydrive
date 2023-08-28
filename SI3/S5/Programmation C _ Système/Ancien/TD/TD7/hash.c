#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hash.h"

struct hash_element{
	char key[11];
	int info;
	struct hash_element *next;
};

struct hash_table
{
	int size; //nombre d'éléments du tableau
	struct hash_element **tab; //pointe vers le début du tableau d'hash_element
};

HashTable hash_new(int size){
	HashTable hash;
	hash = malloc(sizeof(HashTable));
	hash -> size = size;
	hash -> tab = malloc(size * sizeof(HashElement));
	for(int i = 0; i < size; i++){
		hash -> tab[i] = NULL;
	}
	return hash;
}

int hash_value(const char* key){
	int hash = 0;
	/*int i = 1;
	while(key != '\0'){
		hash += ((int)key * i++);
		key++;
	}*/
	for(int i = 0; key[i]; i++){
		hash += (i + 1) * key[i];
	}
	return hash;
}

void hash_add(HashTable table, const char* key, const void* info){
	int index = hash_value(key) % table -> size;
	//ajouter à table -> tab[index];
	HashElement *elem = malloc(sizeof(HashElement));
	strcpy(elem -> key, key);
	elem -> info = info;
	elem -> next = table -> tab[index];
	table -> tab[index] = elem;
}

int hash_find(const HashTable table, const char* key){
	int index = hash_value(key) % table -> size;
	HashElement *search = table -> tab[index];
	while(search != NULL){
		if(strcmp(search -> key, key) == 0){
			return search -> info;
		}
		search = search -> next;
	}
	return -1;
}

int *hash_find_ref(const HashTable table, const char* key){
	int index = hash_value(key) % table -> size;
	HashElement *search = table -> tab[index];
	while(search != NULL){
		if(strcmp(search -> key, key) == 0){
			return &(search -> info);
		}
		search = search -> next;
	}
	return NULL;
}

void hash_print(const HashTable ht){
	printf("Hash table de taille %d", ht -> size);
	for(int i = 0; i < ht -> size; i++){
		printf("%d: ", i);
		while(ht -> tab[i] != NULL){
			printf("'%s' ", ht -> tab[i] -> info);
			(ht -> tab[i])++;
		}
	}
}

