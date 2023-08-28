#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct element {
	int valeur;
	struct element *next;
};

typedef struct element Element;

//Un element à rajouter et un pointeur vers ce nouvel element
Element * push(int v, Element *s){
	Element *p = malloc(sizeof(Element));
	p -> valeur = v;
	p -> next = s;
	return p;
}

void pushV2(int v, Element **s){
	Element *p = malloc(sizeof(Element));
	p -> valeur = v;
	p -> next = *s;
	*s = p;
}

//Prend une pile pour enlever un element
Element * pop(Element *s){
	Element *p = s -> next;
	free(s);
	return p;
}

void popV2(Element **s){
	Element *p = (*s) -> next;
	free(*s);
	*s = p;
}

//Renvoie l'entier du sommet de la pile
int top(Element *s){
	return s -> valeur;
}

//Print
void print_stack(Element *s){
	while(s != NULL){
		printf("Val %d\n", s -> valeur);
		s = s -> next;
	}
	printf("\n");
}


int main(int argc, char const *argv[])
{
	Element * pile = NULL;
	pile = push(2, pile);
	pile = push(4, pile);
	pile = push(6, pile);
	print_stack(pile);
	pile = pop(pile);
	print_stack(pile);
	printf("Top: %d\n", top(pile));
	printf("\n");

	pushV2(3, &pile);
	print_stack(pile);

	popV2(&pile);
	print_stack(pile);
	return 0;
}