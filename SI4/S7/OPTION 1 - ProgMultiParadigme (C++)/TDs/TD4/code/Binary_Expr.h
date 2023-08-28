//=======================================================================
//class Expression
//      Specification and implementation
//-----------------------------------------------------------------------
// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================


#ifndef _BINARY_EXPR_H_
#define _BINARY_EXPR_H_

#include "Expr.h"

class Binary_Expr : public Expr
{
protected:
    Expr *opl; // pointeur obligatoire car n'est pas initialisé à la création de l'expression
    Expr *opr; // idem
public:
    Binary_Expr(Expr &pe1, Expr &pe2) : opl(pe1.clone()), opr(pe2.clone()) {}

    int eval() const = 0;

    virtual Expr* clone() const;
};

#endif
