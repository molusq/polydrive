//=======================================================================
//class Expression
//      Specification and implementation
//-----------------------------------------------------------------------
// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================


#include "Unary_Plus.h"

int Unary_Plus::eval() const {
    return op->eval();
}
