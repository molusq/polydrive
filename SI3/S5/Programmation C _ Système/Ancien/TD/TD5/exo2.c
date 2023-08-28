#include <stdio.h>
#include <stdlib.h>

struct element {
	int valeur;
	struct element *next;
};

typedef struct element Element;
typedef Element *List;

//Ajouter au début de la liste
List prepend_element(List lst, int v){
	Element *p = malloc(sizeof(Element));
	p -> valeur = v;
	p -> next = lst;
	return lst = p;
}

List append_element(List lst, int v){
	if(lst == NULL){
		return prepend_element(lst, v);
	}
	List e = malloc(sizeof(Element));
	e -> valeur = v;
	for(Element *p = lst; p; p = p -> next){
		if(p -> next == NULL){
			p -> next = e;
			return lst;
		}
	}
	return NULL;
}

List insert(List lst, int v){
	if(lst == NULL){
		return prepend_element(lst, v);
	}
	for(List p = lst; p; p = p -> next){
		if((*p).valeur > v && (*(p -> next)).valeur < v){
			List e = malloc(sizeof(Element));
			e -> valeur = v;
			e -> next = p -> next;
			p -> next = e;
			return lst;
		}
	}
	return lst;
}

List delete_element(List lst, int v){
	if(lst == NULL){
		return NULL;
	}
	for(List p = lst; p; p = p -> next){
		if((*(p -> next)).valeur == v){
			p -> next = (*(p -> next)).next;
			return lst;
		}
	}
	return lst;
}

int find_element(List lst, int v){
	while(lst != NULL){
		if((*lst).valeur == v) break;
		else lst = (*lst).next;
	}
	return (lst != NULL) ? 1 : 0;
}

void print_list(List lst){
	while(lst != NULL){
		printf("Val %d\n", lst -> valeur);
		lst = lst -> next;
	}
	printf("\n");
}

int main(){
	List lst = NULL;
	lst = prepend_element(lst, 2);
	lst = append_element(lst, 3);
	lst = prepend_element(lst, 4);
	lst = prepend_element(lst, 6);
	lst = append_element(lst, 1);
	print_list(lst);

	lst = insert(lst, 8);
	print_list(lst);

	lst = insert(lst, 5);
	print_list(lst);

	lst = delete_element(lst, 6);
	print_list(lst);

	printf("%d\n", find_element(lst, 6));
	return 0;
}