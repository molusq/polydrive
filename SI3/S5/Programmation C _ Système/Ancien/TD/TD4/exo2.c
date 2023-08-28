#include <stdio.h>
#include <string.h>


int carre(int n){
	return n*n;
}

void map(int (*fct)(int), int t[], int n){
	printf("{");
	for(int i = 0; i < n; i++){
		t[i] = fct(t[i]);
		printf("%d ",t[i]);
	}
	printf("}\n");
}

int main(){
	int t[] = {1, 2, 3 , 4, 5};
	map(carre, t, 5);
}