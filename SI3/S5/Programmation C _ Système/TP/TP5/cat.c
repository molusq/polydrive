#include <stdio.h>
#include <stdlib.h>
#include <string.h>




int main(int argc, char * argv[]) {

    char c;
    int i = 2;
    int tmp = 0;
    int n = 0;
    int E = 0;
    int h = 0;

    for(int i = 0; i < argc; i++)
        if(strcmp(argv[i], "-n") == 0)
            n = 1;
        else if(strcmp(argv[i], "-E") == 0)
            E = 1;
        else if(strcmp(argv[i], "-h") == 0)
            h = 1;

    if(n == 1)
        printf("1 ");

    if(!h) {
        while((c = getchar()) != EOF) {

            if(tmp) {
                printf("%d ", i++);
                tmp = 0;
            }

            if(n == 1 && c == '\n')
                tmp = 1;

            if(E == 1 && c == '\n')
                printf("$");

            printf("%c", c);
        }
    }

    else
        printf("Flemme de faire une help mais voilà en gros ça marche\n");

    return 0;
}