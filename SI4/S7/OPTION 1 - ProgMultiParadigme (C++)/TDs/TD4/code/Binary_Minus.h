//=======================================================================
//class Expression
//      Specification and implementation
//-----------------------------------------------------------------------

// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================


#ifndef _BINARY_MINUS_H_
#define _BINARY_MINUS_H_

#include "Binary_Expr.h"


class Binary_Minus : public Binary_Expr {
public:
    Binary_Minus(Expr &pe1, Expr &pe2) : Binary_Expr(pe1, pe2) {}

    int eval() const;
};


#endif
