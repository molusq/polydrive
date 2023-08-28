#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[])
{
	int i = 1;
	if(strcmp(argv[i],"-n") == 0)i++;
	while(i < argc){
		printf("%s ", argv[i]);
		i++;
	}
	if(strcmp(argv[1],"-n") != 0)printf("\n");
	return 0;
}