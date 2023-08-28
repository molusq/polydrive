//
// Created by Florian Salord on 04/10/2018.
//

#include <stdbool.h>
#include <stdio.h>
#include "calc.h"

int next;

bool E();

bool Ep();

bool T();

bool Tp();

bool F();

int yylval;

extern int yylex();


bool verify(int tok) {
    if (next != tok) {
        fprintf(stderr, "Invalid token '%c'\n", next);
        return false;
    }
    next = yylex();
    return true;
}

bool E() {
    switch (next) {
        case NUMBER:
        case OPEN:
            return T() && Ep();
        default:
            fprintf(stderr, "Expected INT or OPEN\n");
            return false;
    }
}

bool Ep() {
    switch (next) {
        case PLUS:
            if (verify(PLUS) && T()) {
                printf("PLUS\n");
                return Ep();
            } else return false;
        case MINUS:
            if (verify(MINUS) && T()) {
                printf("MINUS\n");
                return Ep();
            } else return false;
        case CLOSE:
        case EOL:
            return true;
        default:
            fprintf(stderr, "Expected PLUS, MINUS, CLOSE or EOL\n");
            return false;
    }
}

bool T() {
    switch (next) {
        case NUMBER:
        case OPEN:
            return F() && Tp();
        default:
            fprintf(stderr, "Expected INT or OPEN\n");
            return false;
    }
}

bool Tp() {
    switch (next) {
        case PLUS:
        case MINUS:
        case CLOSE:
        case EOL:
            return true;
        case MULT:
            return verify(MULT) && F() && Tp();
        case DIV:
            return verify(DIV) && F() && Tp();
        default:
            fprintf(stderr, "Expected PLUS, MINUS, CLOSE, EOL, MULT or DIV\n");
            return false;
    }
}

bool F() {
    switch (next) {
        case NUMBER:
            printf("PUSH %d\n", yylval);
            return verify(NUMBER);
        case OPEN:
            return verify(OPEN) && E() && verify(CLOSE);
        default:
            fprintf(stderr, "Expected INT or OPEN\n");
            return false;
    }
}

int main() {
    next = yylex();
    printf(">Analyse = %d\n", E());
}