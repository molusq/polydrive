#include <stdio.h>
#include <string.h>
#include <math.h>


void printNum(int i, int base){
	if(i != 0){
		printNum(i/base,base);
		putchar(i%base+'0');
	}
}


void printNumClean(int i, int base){
	if(i >= base){
		printNumClean(i/base, base);
	}
	if(i%base<10){
		putchar(i%base+'0');
	}
	else putchar(i%base-10+'A');
}

/*
*	%10 -> 132%10 = 2 reste de la division
*	/10 -> 132/10 = 13
*	possible de rajouter condition pour i<10
*/


int main(){
	printNumClean(93,16);
	putchar('\n');
}
