#include <stdio.h>
#include <stdlib.h>
#define SIZE 256

int tab[SIZE];

void fillTab(void)
{
	int i;
	for (i = 0; i < SIZE; i++)
	{
		*(tab + i) = i;
	}

}

int main(int argc, char** argv)
{
	if (argc < 1 + 1)
	{
		printf("Usage: %s <tab index>\n", argv[0]);
		exit(1);
	}
	fillTab();
	printf("%d\n", tab[atoi(argv[1])]);

	return 0;
}
