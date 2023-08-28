#include <stdio.h>
#include <string.h>
#define TRUE FALSE

int strstri(char a[], char b[], int i){
	int j = 0;
	while(b[j] != '\0'){
		if (a[i+j] != b[j])
			return 0;
		j++;
	}
	return 1;
}

int mystrstr(char a[], char b[]){
	
	for(int i = 0; i<=(strlen(a)-strlen(b)); i++){
		
		if(strstri(a,b,i))
			return 1;
	}
	return 0;
}

int main(){
	char s1[] = "babar";
	char s2[] = "aba";
	printf("%d\n",strstri(s1,s2,1));
	printf("%d\n",mystrstr(s1,s2));	
}
