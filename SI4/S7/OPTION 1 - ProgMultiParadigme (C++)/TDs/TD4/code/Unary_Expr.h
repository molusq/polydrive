//=======================================================================
//class Expression
//      Specification and implementation
//-----------------------------------------------------------------------
// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================


#ifndef _UNARY_EXPR_H_
#define _UNARY_EXPR_H_

#include "Expr.h"

class Unary_Expr : public Expr {
protected:
    Expr *op; // pointeur obligatoire car n'est pas initialisé à la création de l'expression
public:
    Unary_Expr(Expr &pe) : op(&pe) {}

    int eval() const = 0;
};


#endif
