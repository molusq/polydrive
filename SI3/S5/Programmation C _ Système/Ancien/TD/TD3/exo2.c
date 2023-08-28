#include <stdio.h>
#include <string.h>

void suppressioni(char str[], int i, int n){
	int k=i+n,l=i;
	while(str[l++]=str[k++]);


	/*int longueur = strlen(str);
	for(int j = i; j<longueur; j++){
		str[j] = str[j+n];
	}*/
}

void suppression(char str[], const char suppr[]){
	//int longueur = strlen(suppr);
	int n = 0;
	for(int i = 0; i < (int)strlen(str) ; i++){
		while (str[i+n] == suppr[n])
			n++;
		if(n == strlen(suppr))suppressioni(str,i,n);
		n = 0;
	}
		
}

int main(){
	char mot[] = "Laurent Baptiste ?";
	//suppressioni(mot,14,8);
	suppression(mot,"ent Bap");
	printf("%s",mot);
	return 0;
}
