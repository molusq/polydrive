#include "stdio.h"

int main()
{
	int stop=0;
	int n=-1;
	int b=0;
	int c=0;
	do
	{
		printf("Entrer une valeur \n");
		int a;
		scanf("%d",&a);
		if(a<0)stop=1;
		n++;
		if(a>0)b+=a;
		if(a>c)c=a;
	}while (stop==0);
	printf("Nombre de chiffres positifs : %d \n",n);
	printf("Somme : %d \n",b);
	printf("Valeur maximale : %d \n",c);
	return 0;
}
