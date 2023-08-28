#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int is_option_longue(char * s) {
    if(strlen(s) > 2)
        if(s[0] == '-' && s[1] == '-')
            return 1;
    return 0;
}

int is_option_courte(char * s) {
    if(strlen(s) > 1)
        if(s[0] == '-' && s[1] != '-')
            return 1;
    return 0;
}

int is_end_options(char * s) {
    if(strlen(s) == 2)
        if(s[0] == '-' && s[1] == '-')
            return 1;
    return 0;
}

int is_option_trop_courte(char * s) {
    return strlen(s) == 1;
}

int main(int argc, char * argv[]) {

    int s = 0;

    for(int i = 1; i < argc; i++) {
        if(s == 0) {
            if(is_option_longue(argv[i]))
                printf("Option longue: %s\n", argv[i]);

            else if(is_option_courte(argv[i]))
                for(int j = 1; *(argv[i] + j); j++)
                    printf("Option courte: -%c\n", *(argv[i] + j));

            else if(is_end_options(argv[i]))
                s = 1;

            else if(is_option_trop_courte(argv[i]))
                continue;

            else {
                printf("Argument: %s\n", argv[i]);
                s = 1;
            }
        }


        else
            printf("Argument: %s\n", argv[i]);
        
    }

    return 0;
}