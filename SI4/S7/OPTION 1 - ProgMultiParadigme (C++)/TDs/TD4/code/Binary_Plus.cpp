//=======================================================================
//class Expression
//      Specification and implementation
//-----------------------------------------------------------------------
// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================


#include "Binary_Plus.h"

int Binary_Plus::eval() const {
    return opl->eval() + opr->eval();
}
