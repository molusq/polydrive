/*
 * comp.c        -- Automate déterministe pour ETF -- producion de code 
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 28-Sep-2016 20:13 (eg)
 * Last file update:  4-Oct-2018 13:26 (eg)
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "analyseur-ll.h"


typedef int token;

typedef enum bool {false, true} bool;

// stack manipulation . NO CONTROL (for demonstration purpose only)
static int stack[100];
static int *sp = stack;


// Don't use macros here since we'll have a lot of sequence-point
// problems (thanks to gcc to signal them)

static void push(int v) { *sp++ = v; }                  // No control!
static int  pop(void)   { return *--sp; }
static void add(void)   { push(pop() + pop()); }
static void mul(void)   { push(pop() * pop()); }
static void sub(void)   { int x = pop(); push(pop() - x); }
static void idv(void)   { int x = pop(); push(pop() / x); }  // Division by 0??
static void prstack()   { printf("%d\n", pop()); }


//--- declarations
static int next;            // next token
int yylval;                 // value of previous encountered token

// Advance
static bool advance(void) {
  next = yylex();
  return true;
}

// Verify current token & advance
static bool verify(token tok) {
  if (next != tok) {
    fprintf(stderr, "Invalid token '%d'\n", next);
    return false;
  }
  return advance();
}

static bool E();

// ---------- F Non Terminal
static bool F() {
  switch (next) {
    case INT:
      push(yylval);
      return advance();
    case OPEN:
      return advance() && E()&& verify(CLOSE);
    default:
      fprintf(stderr, "Expected INT or OPEN\n");
      return false;
  }
}


// ---------- T Non Terminal
static bool Tp() {
  int  op;

  switch (op = next) {
    case DIV:
    case MULT:
      if (advance() && F()) {
        if (op == DIV) idv(); else mul();
        return Tp();
      }
      return false;
    case PLUS:
    case MINUS:
    case CLOSE:
    case EOL:
      return true;
    default:
      fprintf(stderr, "Expected MULT, PLUS, or EOL\n");
      return false;
  }
}

static bool T() {
  switch (next) {
    case INT:
    case OPEN:
      return F() && Tp();
    default:
      fprintf(stderr, "Expected INT or OPEN\n");
      return false;
  }
}

// ---------- E Non Terminal
static bool Ep() {
  int  op;

  switch (op = next) {
    case PLUS:
    case MINUS:
      if (advance() && T()) {
        if (op == PLUS) add(); else sub();
        return Ep();
      }
      return false;
    case CLOSE:
    case EOL:
      return true;
    default:
      fprintf(stderr, "Expected PLUS or CLOSE\n");
      return false;
  }
}

static bool E() {
  switch (next) {
    case INT:
    case OPEN:
      return T() && Ep();
    default:
      fprintf(stderr, "Expected INT or OPEN\n");
      return false;
  }
}

static void prompt(void) {
  printf("> "); fflush(stdout);
}

int main() {
  prompt();
  while (advance()) {
    if (next == EOF) break;
    if (E() && next == EOL)
      prstack();
    prompt();
  }
  printf("Bye.\n");

  return 0;
}
