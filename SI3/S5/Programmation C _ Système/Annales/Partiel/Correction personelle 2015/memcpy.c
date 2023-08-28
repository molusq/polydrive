#include <stdio.h>
#include <stdlib.h>

/*
On utilise des paramètres de type void* qui correspondent
à un pointeur de n'importe quel type. On peut donc
manipuler des int, long, unsigned int, ...
Ca permet d'éviter à l'appelant de faire un cast.
*/

void *memcpy(void *dest, const void *src, size_t n) {
    unsigned char *d = dest;
    const unsigned char *s = src;
    while(n--) {
        *d++ = *s++;
    }
}

int main() {
    int array [] = { 54, 85, 20, 63, 21 };
    size_t length = sizeof(array);
    
    /* Memory allocation and copy */
    int *copy = malloc(length);
    memcpy(copy, array, length);
    
    /* Display the copied values */
    for (length = 0; length < 5; length++ ) {
        printf("%d ", copy[length]);
    }
    printf("\n");
    free(copy);
    
    return 0;
}
