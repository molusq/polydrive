//=======================================================================
//class Expression
//      Specification and implementation
//-----------------------------------------------------------------------
// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================


#include "Binary_Div.h"

int Binary_Div::eval() const {
    return opr->eval() == 0 ? throw ZeroDivide() : opl->eval() / opr->eval();
}
