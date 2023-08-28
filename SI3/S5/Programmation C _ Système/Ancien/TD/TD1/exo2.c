#include "stdio.h"

int main()
{
	int stop=0;
	int n=-1;
	do
	{
		printf("Entrer une valeur \n");
		int a;
		scanf("%d",&a);
		if(a<0)stop=1;
		n++;
	}while (stop==0);
	printf("Nombre de chiffres positifs : %d",n);
	return 0;
}
