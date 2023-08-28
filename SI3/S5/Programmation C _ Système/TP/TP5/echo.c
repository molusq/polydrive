#include <stdio.h>
#include <stdlib.h>
#include <string.h>



int main(int argc, char * argv[]) {

    if(strcmp(argv[1], "-r") == 0)
        for(int i = argc - 1; i > 1; i--)
            printf("%s ", argv[i]);

    else
        for(int i = 1; i < argc; i++)
            printf("%s ", argv[i]);

    return 0;
}