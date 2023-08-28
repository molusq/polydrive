/*
 * calc.c 	-- Calculator functions
 *
 * Copyright © 2015 Erick Gallesio - I3S-CNRS/Polytech Nice-Sophia <eg@unice.fr>
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Oct-2015 12:02 (eg)
 * Last file update:  4-Nov-2015 16:49 (eg)
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>

#include "calc.h"

#define initialize_header(p, kind) { \
  AST_LINENO(p) = yylineno;          \
  AST_KIND(p)   = kind;              \
}

extern int yylineno;                    // line number defined by lex

static ast_node *allocate_node(int size);


// ----------------------------------------------------------------------
//		Idents stuff
// ----------------------------------------------------------------------
ast_node *make_ident(char *str) {
    ast_node *p = allocate_node(sizeof(struct ast_ident));

    initialize_header(p, k_ident);
    VAR_NAME(p) = str;
    return p;
}


// ----------------------------------------------------------------------
//		Numbers stuff
// ----------------------------------------------------------------------

ast_node *make_number(float f) {
    ast_node *p = allocate_node(sizeof(struct ast_number));

    initialize_header(p, k_number);
    NUMBER_VALUE(p) = f;
    return p;
}


// ----------------------------------------------------------------------
//		Operators stuff
// ----------------------------------------------------------------------
ast_node *make_operator(int operator, int arity, ...) {
    ast_node *p;
    va_list ap;

    p = allocate_node(sizeof(struct ast_operator) + arity * sizeof(ast_node *));
    initialize_header(p, k_operator);
    OPER_OPERATOR(p) = operator;
    OPER_ARITY(p) = arity;

    // Fill in the operands array from the vararg list
    va_start(ap, arity);
    for (int i = 0; i < arity; i++)
        OPER_OPERANDS(p)[i] = va_arg(ap, ast_node *);
    va_end(ap);

    return p;
}

// ----------------------------------------------------------------------
//		If stuff
// ----------------------------------------------------------------------
ast_node *make_if(ast_node *cmd, ast_node *action1, ast_node *action2) {
    ast_node *p;

    p = allocate_node(sizeof(struct ast_if) + sizeof(cmd) + sizeof(action1) + sizeof(action2));
    initialize_header(p, k_if);
    IF_CMD(p) = cmd;
    IF_ACT1(p) = action1;
    IF_ACT2(p) = action2;

    return p;
}

// ----------------------------------------------------------------------
//		While stuff
// ----------------------------------------------------------------------
ast_node *make_while(ast_node *cond, ast_node *action) {
    ast_node *p;

    p = allocate_node(sizeof(struct ast_if) + sizeof(cond) + sizeof(action));
    initialize_header(p, k_while);
    WHILE_COND(p) = cond;
    WHILE_ACTION(p) = action;

    return p;
}


// ----------------------------------------------------------------------
//		Read stuff
// ----------------------------------------------------------------------
ast_node *make_read(ast_node *ident) {
    ast_node *p = allocate_node(sizeof(struct ast_read));

    initialize_header(p, k_read);
    READ_NAME(p) = VAR_NAME(ident);

    return p;
}


// ----------------------------------------------------------------------
//	Utilities
// ----------------------------------------------------------------------
static ast_node *allocate_node(int size) {
    ast_node *p = malloc(size);

    if (!p) {
        error_msg("out of memory");
        exit(1);
    }
    return p;
}


void free_node(ast_node *p) {
    if (!p) return;

    switch (AST_KIND(p)) {
        case k_number:    /* Nothing to do */ break;
        case k_ident:
            free(VAR_NAME(p));
            break;
        case k_operator:
            for (int i = 0; i < OPER_ARITY(p); i++)
                free(OPER_OPERANDS(p)[i]);
            break;
        case k_if:
            break;
        case k_while:
            break;
        case k_read:
            break;
    }
    free(p);
}


void error_msg(const char *format, ...) {
    va_list ap;

    fprintf(stderr, "*** Error: ");
    va_start(ap, format);
    vfprintf(stderr, format, ap);
    va_end(ap);
}
