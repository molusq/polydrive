#include <stdio.h>
#include <stdlib.h>

int main()
{
	int a;
	int b=1;
	//printf("%d ",b);
	while((a=getchar())!=EOF){
		putchar(a);
		if(a==10) {
			b++;
			printf("%d ",b);
		}
	}
	return 0;
}
