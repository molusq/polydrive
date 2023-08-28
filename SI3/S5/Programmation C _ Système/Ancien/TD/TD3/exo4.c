#include <stdio.h>

void en_binaireTest(int n){
	int nb = 0;
	int	binaire = 0;
	int i = 1;
	
	while (n != 0){
	nb = n%2;
	n /= 2;
	binaire += nb*i;
	i *= 10;
	}
	printf("%d",binaire);
}

void en_binaire(int n){
	if(n >= 2)
		en_binaire(n/2);
	putchar(n%2 + '0');
}

void en_binaireOpTest(int n){
	int i = 31;
	/*while(n>>i == 0){
		i--;
	}*/
	while(i!=-1){
		putchar('0' + ((n>>i)&1));
		i--;
	}
}

/* 8*int = 32 bits
 * &1 pour transformer le dernier bit en 0 ou 1
 */
void en_binaireOp(int n){
	for(int i = sizeof(n)*8-1; i >=0; i--){
		putchar('0' + ((n>>i)&1));
	}
}

int main(){
	en_binaireOp(256);
}
