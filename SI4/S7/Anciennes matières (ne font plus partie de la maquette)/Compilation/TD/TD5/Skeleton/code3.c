/*
 * code2.c 	-- Production of code for a stack machine
 *
 * Copyright © 2015 Erick Gallesio - I3S-CNRS/Polytech Nice-Sophia <eg@unice.fr>
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 22-Oct-2015 22:48 (eg)
 * Last file update:  4-Nov-2015 17:09 (eg)
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "calc.h"
#include "syntax.h"

void eval(ast_node *n);

int label = 1;
int last_stm = 1;

#define BOX(type, color)    printf("\tbox%d [label=\"[%s]\", fillcolor=\"%s\"];\n", label++, type, color)
#define BOXS(type, color)    printf("\tbox%d [label=\"%s\", fillcolor=\"%s\"];\n", label++, type, color)
#define BOXI(type, color)    printf("\tbox%d [label=\"%d\", fillcolor=\"%s\"];\n", label++, (int)type, color)
#define LINK(in, out)       printf("\t\tbox%d -> box%d;\n", in, out)

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
//			This version produces a dot file
// ---------------------------------------------------------------------
void produce_code(ast_node *n) {
    printf("digraph E{\n");
    printf("\tnode [style=\"filled\"];\n");
    // printf("\tGraph of %p\n", n);
    //BOX(";", "turquoise4");
    eval(n);
    printf("}\n");
}

void k_operator_eval(ast_node *n, int in_label) {
    ast_node **op = OPER_OPERANDS(n);
    eval(op[0]);
    LINK(in_label, in_label + 1);
    eval(op[1]);
    LINK(in_label, in_label + 2);
}

void eval(ast_node *n) {
    int in_label = label;
    //int out_label = label + 1;
    //printf("Produire du code pour n = %p!!!!!\n", n);
    if (!n) return;

    switch (AST_KIND(n)) {
        case k_number:
            // PROD1F("push", NUMBER_VALUE(n));
            BOXI(NUMBER_VALUE(n), "tomato");
            return;

        case k_ident:
            // PROD1S("load", VAR_NAME(n));
            BOXS(VAR_NAME(n), "peru");
            return;

        case k_if:;
            BOXS("if", "turquoise4");
            eval(IF_CMD(n));
            LINK(in_label, in_label + 1);
            eval(IF_ACT1(n));
            LINK(in_label, last_stm);
            if (IF_ACT2(n)) {
                eval(IF_ACT2(n));
                LINK(in_label, last_stm);
            }
            last_stm = in_label;
            return;

        case k_while:
            BOXS("while", "turquoise4");
            eval(WHILE_COND(n));
            LINK(in_label, in_label + 1);
            eval(WHILE_ACTION(n));
            LINK(in_label, last_stm);
            last_stm = in_label;
            return;

        case k_read:
            BOXS("read", "turquoise4");
            BOXS(READ_NAME(n), "peru");
            LINK(in_label, in_label + 1);
            last_stm = in_label;
            return;

        case k_operator: {
            ast_node **op = OPER_OPERANDS(n);
            int arity = OPER_ARITY(n);

            switch (OPER_OPERATOR(n)) {
                /* Expressions */
                case UMINUS:
                    BOX("_", "turquoise4");
                    eval(op[0]);
                    LINK(in_label, in_label + 1);
                    return;
                case '+':
                    BOX("+", "turquoise4");
                    k_operator_eval(n, in_label);
                    return;
                case '-':
                    BOX("-", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case '*':
                    BOX("*", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case '/':
                    BOX("/", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case '<':
                    BOX("<", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case '>':
                    BOX(">", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case GE:
                    BOX(">=", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case LE:
                    BOX("<=", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case NE:
                    BOX("!=", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                case EQ:
                    BOX("==", "turquoise4");
                    k_operator_eval(n, in_label);

                    return;
                    /* Statements */
                case ';':
                    if (arity == 0) return;
                    else {
                        BOX(";", "turquoise4");
                        eval(op[0]);
                        LINK(in_label, last_stm);
                        eval(op[1]);
                        LINK(in_label, last_stm);
                        last_stm = in_label;
                    }
                    return;
                case KPRINT:
                    BOXS("print", "turquoise4");
                    eval(op[0]);
                    LINK(in_label, in_label + 1);
                    last_stm = in_label;
                    return;
                case '=':
                    last_stm = in_label;
                    BOX("=", "turquoise4");
                    k_operator_eval(n, in_label);
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