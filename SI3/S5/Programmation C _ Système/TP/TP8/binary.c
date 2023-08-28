#include <stdlib.h>
#include <stdio.h>


void en_binaire(int n) {
    if (n > 1) {
        en_binaire(n / 2);
    }
    putchar('0' + n % 2);
}

void en_binaire_bit(int n) {
    int i;
    for (i = 31; i >= 0; i--) { // 32 bits = 4 octets c'est le nb de bits d'un int en C sur architecture x64
        putchar('0' + (n >> i & 1));
    }
}

int main(void) {
    en_binaire(5);
    putchar('\n');
    en_binaire(10);
    putchar('\n');
    en_binaire(15);
    putchar('\n');
    en_binaire(52);
    putchar('\n');

    en_binaire_bit(5);
    putchar('\n');
    en_binaire_bit(10);
    putchar('\n');
    en_binaire_bit(15);
    putchar('\n');
    en_binaire_bit(52);
    putchar('\n');

    return 0;
}