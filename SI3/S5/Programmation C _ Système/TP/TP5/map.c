#include <stdio.h>
#include <stdlib.h>



void map(int (*f)(int), int a[], int l) {
    for(int i = 0; i < l; i++)
        a[i] = f(a[i]);
}

void print_array(int a[], int l) {
    printf("[ ");
    for(int i = 0; i < l; i++)
        printf("%d ", a[i]);
    printf("]");
}

int carre(int n) {
    return n * n;
}

int main(void) {

    int a[5] = {1, 2, 3, 4, 5};

    print_array(a, 5);

    printf("\n*** Applying carre(int) function ***\n");

    map(carre, a, 5);

    print_array(a, 5);

    return 0;
}