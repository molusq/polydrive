/*
 * code2.c 	-- Production of code for a stack machine
 *
 * Copyright © 2015 Erick Gallesio - I3S-CNRS/Polytech Nice-Sophia <eg@unice.fr>
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Oct-2015 15:31 (eg)
 * Last file update:  4-Nov-2015 17:06 (eg)
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "calc.h"
#include "syntax.h"

// ----------------------------------------------------------------------
//		Utilities
// ---------------------------------------------------------------------
#define LABEL(n)     printf("L%03d:\n", n);            // output a label

#define PROD0(op)     printf("\t%s\n", op)
#define PROD1F(op, v)     printf("\t%s\t%g\n", op, v)    // v is a float
#define PROD1S(op, v)     printf("\t%s\t%s\n", op, v)    // v is a string
#define PROD1L(op, v)     printf("\t%s\tL%03d\n", op, v) // v is a label

int label = 0;

struct var_cell {        // Type for representing variables
    char *name;
    float value;
    struct var_cell *next;
};

static struct var_cell *all = NULL;

static float get_ident_value(char *id) {
    for (struct var_cell *cur = all; cur; cur = cur->next) {
        if (strcmp(cur->name, id) == 0) return cur->value;
    }
    error_msg("variable '%s' is unset.\n", id);
    return 0;
}

static float set_ident_value(char *id, float value) {
    for (struct var_cell *cur = all; cur; cur = cur->next) {
        if (strcmp(cur->name, id) == 0) {
            cur->value = value;
            return value;
        }
    }

    // Var not found create a new cell and place it in front of the 'all' list
    struct var_cell *p = malloc(sizeof(struct var_cell));

    if (!p) {
        error_msg("Cannot allocate memory for variable '%s'.\n", id);
        exit(1);
    }
    p->name = strdup(id);
    p->value = value;
    p->next = all;
    all = p;

    return value;
}

// ----------------------------------------------------------------------
//		Code production
//			This version produces code for a stack machine
// ---------------------------------------------------------------------
void produce_code(ast_node *n) {
    //printf("Produire du code pour n = %p!!!!!\n", n);
    if (!n) return;

    switch (AST_KIND(n)) {
        case k_number:
            PROD1F("push", NUMBER_VALUE(n));
            return;

        case k_ident:
            PROD1S("load", VAR_NAME(n));
            return;

        case k_if:;
            int in_if_label = label++;
            int out_if_label = label;
            produce_code(IF_CMD(n));
            PROD1L("jumpz", in_if_label);
            produce_code(IF_ACT1(n));
            if (IF_ACT2(n)) {
                label++;
                PROD1L("jump", out_if_label);
                LABEL(in_if_label++);
                produce_code(IF_ACT2(n));
            }
            LABEL(in_if_label);
            return;

        case k_while:;
            int in_label = label++;
            int out_label = label++;
            LABEL(in_label);
            produce_code(WHILE_COND(n));
            PROD1L("jumpz", out_label);
            produce_code(WHILE_ACTION(n));
            PROD1L("jump", in_label);
            LABEL(out_label);
            return;

        case k_read:
            PROD1S("read", READ_NAME(n));
            return;

        case k_operator: {
            ast_node **op = OPER_OPERANDS(n);
            int arity = OPER_ARITY(n);

            switch (OPER_OPERATOR(n)) {
                /* Expressions */
                case UMINUS:
                    produce_code(op[0]);
                    PROD0("negate");
                    return;
                case '+':
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("add");
                    return;
                case '-':
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("sub");
                    return;
                case '*':
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("mul");
                    return;
                case '/':
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("div");
                    return;
                case '<':
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("cmplt");
                    return;
                case '>':
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("cmpgt");
                    return;
                case GE:
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("cmpge");
                    return;
                case LE:
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("cmple");
                    return;
                case NE:
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("cmpne");
                    return;
                case EQ:
                    produce_code(op[0]);
                    produce_code(op[1]);
                    PROD0("cmpeq");
                    return;
                    /* Statements */
                case ';':
                    if (arity == 0) return;
                    else {
                        produce_code(op[0]);
                        produce_code(op[1]);
                    }
                    return;
                case KPRINT:
                    produce_code(op[0]);
                    PROD0("print");
                    return;
                case '=':
                    produce_code(op[1]);
                    PROD1S("store", VAR_NAME(op[0]));
                    return;
                default:
                    error_msg("Houston, we have a problem: unattended token %d",
                              OPER_OPERATOR(n));
                    return;
            }
        }
        default:
            return;
    }
}