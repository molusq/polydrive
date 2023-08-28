#include <stdio.h>
#include <stdlib.h>


char * strcpy(char * dest, const char * src) {
    char * new_str = dest;

    int i = 0;
    while((*(new_str + i) = *(src + i)))
        i++;

    return new_str;
}

size_t strlen(const char *s) {
    size_t i = 0;
    while(*(s + i))
        i++;
    return i;
}

char * strdup(const char *s) {
    char * dupl = (char *)malloc(strlen(s) + 1);
    int i = 0;
    while(*(s + i)) {
        *(dupl + i) = *(s + i);
        i++;
    }
    *(dupl + i) = '\0';
    return dupl;
}

char * strchr(const char *s, int c) {
    // Une deuxième manière d'itérer plutôt que le while...
    for(int i = 0; *(s + i); i++) {
        if(*(s + i) == c) {
            return s + i;
        }
    }
    return NULL;
}

/* NE FAIT PAS PARTIE DU TP, SEULEMENT UTILISEE POUR MONTRER FONCTIONNEMENT DE STRDUP */
void strupper(char s[]) {
    for(int i = 0; i < strlen(s); i++)
        if((int)(s[i]) >= 97 && (int)(s[i]) <= 122)
            s[i] = (char)((int)(s[i]) - 32);
}

int main(void) {

    // ----------- STRCPY -----------
    
    char s1[20] = "salut";
    char * s2 = "bonjour";
    char * s3 = (char *)malloc(101);

    strcpy(s1, s2);

    printf("s1 = salut\ns2 = bonjour\ns3 = EMPTY\n\n");
    printf("strcpy s2 -> s1 = %s\n", s1);

    strcpy(s3, s2);

    printf("strcpy s2 -> s3 = %s\n", s3);

    strcpy(s3, "ceci est un test");

    printf("strcpy \"ceci est un test\" -> s3 = %s\n\n", s3);

    free(s3);

    // ----------- STRLEN -----------

    char * test = "phrase de 17 char";

    printf("strlen(\"phrase de 17 char\") = %I64d\n\n", strlen(test));

    // ----------- STRDUP -----------

    const char * original = "This is a string";
    char * copy = strdup(original);

    printf("origin: %s\ndup: %s\n", original, copy);
    printf("***Applying upper to the copy to show that the original won't be affected***\n");

    strupper(copy);

    printf("origin: %s\ndup: %s\n\n", original, copy);

    free(copy);

    // ----------- STRCHR -----------

    const char * source = "The C Language";
    char * destination;
    char * pointer;

    printf("Let's try to change every 'a' in 'A' in \"%s\"\n", source);

    destination = (char *)malloc(strlen(source) + 1);
    strcpy(destination, source);

    while((pointer = strchr(destination, 'a')))
        *pointer = 'A';

    printf("Result, thanks to strchr: %s\n", destination);

    free(destination);

    return 0;
}