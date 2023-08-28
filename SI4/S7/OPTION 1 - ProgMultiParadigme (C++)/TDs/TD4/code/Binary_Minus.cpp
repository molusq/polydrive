//
// Created by Florian Salord on 26/10/2018.
//

#include "Binary_Minus.h"

int Binary_Minus::eval() const {
    return opl->eval() - opr->eval();
}
