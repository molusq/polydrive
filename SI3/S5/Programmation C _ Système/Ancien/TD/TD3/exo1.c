#include <stdio.h>
#include <math.h>
#include <string.h>

int palindrome(char str[]){
	int i,j;
	for(i = 0, j = (strlen(str)-1); i<(strlen(str)/2); i++, j--){
		if(str[i]!=str[j]) return 0;
	}
	return 1;
}

int main(){
	char str[] = "bonobo";
	if(palindrome(str))
		printf("%s est un palindrome",str);
	else printf("%s n'est pas un palindrome",str);
	return 0;
}
