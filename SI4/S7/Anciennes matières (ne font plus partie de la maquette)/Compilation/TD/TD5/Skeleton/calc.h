/*
 * calc.h 	-- Calc types
 *
 * Copyright © 2015 Erick Gallesio - I3S-CNRS/Polytech Nice-Sophia <eg@unice.fr>
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date: 21-Oct-2015 11:20 (eg)
 * Last file update:  4-Nov-2015 16:46 (eg)
 */

#ifndef _CALC_H_
#define _CALC_H_

/// The various kind of node we can have: ident, number, operator
enum node_kind {
    k_ident, k_number, k_operator, k_if, k_while, k_read
};


// ----------------------------------------------------------------------
//		AST node type definition
// ----------------------------------------------------------------------
typedef struct ast_node ast_node;

struct ast_node {
    int lineno;                   // source line number of the node
    enum node_kind kind;        // kind of node
};

#define AST_LINENO(p)        (((ast_node *)(p))->lineno)
#define AST_KIND(p)        (((ast_node *)(p))->kind)

// ----------------------------------------------------------------------
//		Idents stuff
// ----------------------------------------------------------------------
struct ast_ident {
    ast_node header;        // AST header
    char *name;            // name of the ident
};

#define VAR_NAME(p)        (((struct ast_ident *) p)->name)

ast_node *make_ident(char *str);    // make a ident node


// ----------------------------------------------------------------------
//		Numbers stuff
// ----------------------------------------------------------------------
struct ast_number {
    ast_node header;              // AST header
    float value;            // value of the number
};

#define NUMBER_VALUE(p)    (((struct ast_number *) p)->value)

ast_node *make_number(float f);    // make a number node


// ----------------------------------------------------------------------
//		Operators stuff
// ----------------------------------------------------------------------
struct ast_operator {
    ast_node header;        // AST header
    int operator;            // lex token
    int arity;            // arity of the operator
    ast_node *operands[0];    // array of operands
};

#define OPER_OPERATOR(p)    (((struct ast_operator *) p)->operator)
#define OPER_ARITY(p)        (((struct ast_operator *) p)->arity)
#define OPER_OPERANDS(p)    (((struct ast_operator *) p)->operands)

ast_node *make_operator(int operator, int arity, ...); // make an operator node


// ----------------------------------------------------------------------
//		If stuff
// ----------------------------------------------------------------------
struct ast_if {
    ast_node header;
    ast_node *cmd;
    ast_node *action1;
    ast_node *action2;
};

#define IF_CMD(p) (((struct ast_if *) p)->cmd)
#define IF_ACT1(p) (((struct ast_if *) p)->action1)
#define IF_ACT2(p) (((struct ast_if *) p)->action2)

ast_node *make_if(ast_node *, ast_node *, ast_node *);

// ----------------------------------------------------------------------
//		While stuff
// ----------------------------------------------------------------------
struct ast_while {
    ast_node header;
    ast_node *cond;
    ast_node *action;
};

#define WHILE_COND(p) (((struct ast_while *) p)->cond)
#define WHILE_ACTION(p) (((struct ast_while *) p)->action)

ast_node *make_while(ast_node *, ast_node *);

// ----------------------------------------------------------------------
//		Read stuff
// ----------------------------------------------------------------------
struct ast_read {
    ast_node header;
    char *name;
    float value;
};

#define READ_NAME(p) (((struct ast_read *) p)->name)
#define READ_VALUE(p) (((struct ast_read *) p)->value)

ast_node *make_read(ast_node *);


// ----------------------------------------------------------------------
//		Utilities
// ----------------------------------------------------------------------
void error_msg(const char *format, ...);        // Display an error message
void free_node(ast_node *p);                    // Freeing a node



#endif /* _CALC_H_ */
