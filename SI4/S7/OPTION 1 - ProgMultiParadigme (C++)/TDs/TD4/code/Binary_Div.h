//=======================================================================
//class Expression
//      Specification and implementation
//-----------------------------------------------------------------------

// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================


#ifndef _BINARY_DIV_H_
#define _BINARY_DIV_H_

#include "Binary_Expr.h"


class Binary_Div : public Binary_Expr {
public:
    Binary_Div(Expr &pe1, Expr &pe2) : Binary_Expr(pe1, pe2) {}

    int eval() const;
};

#endif
