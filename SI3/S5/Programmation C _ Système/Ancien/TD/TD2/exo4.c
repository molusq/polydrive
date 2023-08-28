#include <stdio.h>

int main(){
	int input;
	int n = 0;
	int tab[16];
	while((input=getchar()) != EOF){
		if (n>16 || input == '\n'){
			for(int i=0;i<n;i++)
				printf("%02x ",tab[i]);
			for(int i=0;i<n;i++)
				printf("%c",tab[i]);
			putchar('\n');
			n=0;
		}
		
		tab[n]=input;
		n++;
		
	}
}
