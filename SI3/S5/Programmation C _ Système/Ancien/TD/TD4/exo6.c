#include <stdio.h>
#include <string.h>

/*
int main(int argc, char const *argv[])
{
	int i = 1;
	while(i < argc){
		if(argv[i][0] == '-' && argv[i][1] == '-'){
			if(argv[i][2] != '\0'){
					printf("Option longue: %s\n", argv[i]);
			}
			else i++;
		}
		else if(argv[i][0] == '-'){
			if(argv[i][1] != '\0'){
				for(int j = 1; j < strlen(argv[i]); j++){
					printf("Option courte: %c\n", argv[i][j]);
				}
			} else i++;
		}
		else break;
		i++;
	}

	while(i < argc){
		printf("Argument: %s\n", argv[i]);
		i++;
	}
	return 0;
}
*/

int main(int argc, char const *argv[])
{
	int opt = 1;
	argc--;
	argv++;
	while(argc){
		if(opt){
			if(argv[0][0] == '-' && argv[0][1] == '-'){
				if(argv[0][2] == '\0') opt = 0;
				else printf("Option longue: %s\n", argv[0]);
			}
			else if(argv[0][0] == '-'){
				if(argv[0][1] != '\0'){
					for(int j = 1; j < strlen(argv[0]); j++){
						printf("Option courte: %c\n", argv[0][j]);
					}
				}
			}
			else {
				opt = 0;
				printf("Argument: %s\n", argv[0]);
			}
		}
		else printf("Argument: %s\n", *argv);
		argc--;
		argv++;
	}

	return 0;
}
