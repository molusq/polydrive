#include <stdio.h>
#include <stdlib.h>

int somme(int a, int b) {
    return a + b;
}

int max(int a, int b) {
    return a > b ? a : b;
}

int prod(int a, int b) {
    return a * b;
}

int iterate(int (*f)(int, int), int a[], int l) {
    if(l == 1)
        return a[0];

    return (*f)(iterate((*f), a, l - 1), a[l - 1]);
}

int main(void) {

    int tab[] = {1, 2, 3, 4, 5};
    int r = iterate(max, tab, 5);
    printf("Max: %d\n", r);
    r = iterate(somme, tab, 5);
    printf("Somme: %d\n", r);
    r = iterate(prod, tab, 5);
    printf("Produit: %d\n", r);

    return 0;
}