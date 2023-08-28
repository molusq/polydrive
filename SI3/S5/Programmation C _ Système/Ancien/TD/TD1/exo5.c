#include <stdio.h>
#include <math.h>

int main(){
	double f;
	double c=0;
	for(double i=0;i<=20;i+=0.5)
	{
		f=(9*c/5)+32;
		rint(f);
		printf("%.1f°C = %f°F\n",c,f);
		c+=0.5;
	}
}
