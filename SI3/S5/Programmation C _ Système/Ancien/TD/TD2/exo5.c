#include <stdio.h>

void mystrcpy(char s1[],char s2[]){
	int i=0,j=0;
	
	while(s1[i] != '\0')
		i++;
	
	while(s2[j] != '\0'){
		s1[i] = s2[j];
		i++;
		j++;
	}
	s1[i] = '\0';
}

int mystrcmp(char s1[],char s2[]){
	int cmp = 0;
	
	int size_s1 = 0;
	while(s1[size_s1] != '\0')
		size_s1++;
	
	int size_s2 = 0;
	while(s2[size_s2] != '\0')
		size_s2++;
	
	if(size_s1 == size_s2)
		cmp = 0;
	else if(size_s1 < size_s2)
		cmp = -1;
	else if(size_s1 > size_s2)
		cmp = 1;
	return cmp;
}

void mystrupper(char s[]){
	int i = 0;
	
	while(s[i] != '\0'){
		if(s[i]<=122 && s[i]>=97){
			s[i]-=32;
		}
		i++;
	}
}

int main(){
	char s1[20] = "test_s1";
	char s2[20] = " suite test_2";
	printf("Comparaison: %d\n",mystrcmp(s1,s2));
	mystrcpy(s1,s2);
	printf("%s\n",s1);
	mystrupper(s1);
	printf("%s\n",s1);
}
