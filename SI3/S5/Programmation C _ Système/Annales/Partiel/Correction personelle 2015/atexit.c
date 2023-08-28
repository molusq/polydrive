#include <stdio.h>

#define MAX_FUNC 100

void *fonctions_tab[MAX_FUNC];
//typedef void (*function)(void);
//function fonctions_tab[MAX_FUNC];
int fonctions = 0;

int atexit(void (*function)(void)) {
    if (fonctions < MAX_FUNC) {
        fonctions_tab[fonctions++] = function;
        return 0;
    }
    return -1;
}

void customExit() {
    for (int i = fonctions - 1; i >= 0; i--) {
        void (*f)(void) = fonctions_tab[i];
        f();
        //(*(fonctions_tab[i]))();
    }
}

void print1() {
    printf("Print 1\n");
}

void print2() {
    printf("Print 2\n");
}

int main() {
    atexit(print1);
    atexit(print2);
    printf("Hello World\n");
    customExit();
    return 0;
}
