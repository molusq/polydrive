#include <stdio.h>
#include <stdarg.h>


int evaluer(char operateur, int operande, ...){
	va_list ap;
	va_start(ap,operande);
	operande = va_arg(ap, int);
	int evaluation = operande;
	while(operande > 0){
		switch(operateur){
		case '+':
			evaluation += operande;
		case '-':
			evaluation -= operande;
		case '*':
			evaluation *= operande;
		case '/':
			evaluation /= operande;
		}
		operande = va_arg(ap, int);
	}
	va_end(ap);
	return evaluation;
}

int main()
{
	printf("%d\n", evaluer('+', 1, 2, 3, -1));
	printf("%d\n", evaluer('-', 10, evaluer('*', 2, 2, 2, -1)));
	return 0;
}