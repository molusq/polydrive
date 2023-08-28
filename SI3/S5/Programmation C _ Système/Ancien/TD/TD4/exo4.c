#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *mystrcpy(char *dest, const char *src){
	char *save = dest;
	while((*dest++ = *src++));
	return save;
}

size_t mystrlen(const char *s){
	int i = 0;
	for(; *s != '\0'; i++, s++);
	return i;
}

char *mystrdup(const char *s){
	char *res = malloc(mystrlen(s)+1);
	return res ? mystrcpy(res, s) : NULL;
}

char *mystrchr(const char *s, int c){
	while((*s++ != c));
	return mystrdup(s);
}

int main(){
	const char *src = "Hello World!";
	char *dest = malloc(mystrlen(src)+1);
	printf("%s\n", src);
	mystrcpy(dest, src);
	printf("%s\n", dest);
	printf("%d\n", (int)mystrlen(dest));
	printf("%s\n", mystrcpy(dest, src));
	printf("%s\n", mystrdup(src));
	printf("%s\n", mystrchr(src, ' '));
	return 0;
}