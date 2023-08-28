#include <stdlib.h>
#include <stdio.h>


void print_num_base_lol(int n, int b) {
    if(n >= b)
        print_num_base_lol(n / b, b);

    if(n % b < 10)
        putchar(n % b + '0');

    else
        putchar(n % b - 10 + 'A');
}

void print_num_base(int n, int b) {
    if(n < 0) {
        putchar('-');
        print_num_base_lol(-n, b);
    }
    else
        print_num_base_lol(n, b);
}

int main(void) {
    

    return 0;
}