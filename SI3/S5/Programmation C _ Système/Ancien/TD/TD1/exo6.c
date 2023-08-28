#include <stdio.h>

int main(){
	int input;
	int tab[128]={0};
	while((input=getchar())!=EOF){
		if(input=='\n')
			break;
		tab[input]+=1;
	}
	for(int i=0;i<128;i++)
	{
		if(tab[i]!=0)
			printf("La lettre %c ete ecrite %d fois",i,tab[i]);
	}
	return 0;
}
