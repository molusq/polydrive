#include <stdio.h>

int main()
{
	int nchar = 0;
	int nlignes = 1;
	int nmots = 0;
	int input;
	while((input = getchar()) != EOF)
	{
		if(input == '0')
			break;
		else if(input == '\n')
		{
			nlignes++;
			nmots++;
		}
		else if(input == ' ')
			nmots++;
		else if(input !='\n' && input!=' ' && input != '0')
			nchar++;
	}
	printf("Il y a %d characteres\n",nchar);
	printf("Il y a %d lignes\n",nlignes);
	printf("Il y a %d mots\n",nmots);
	return 0;
}

