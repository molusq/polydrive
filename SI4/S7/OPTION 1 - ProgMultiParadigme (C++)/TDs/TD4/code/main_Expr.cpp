//=======================================================================
// class Expression
//      Utilization
//-----------------------------------------------------------------------
// Julien DeAntoni (inspired from Jean-Paul Rigault course)
// April 2010
//=======================================================================

#include <iostream>

using namespace std;

#include "Expr.h"
#include "Binary_Div.h"
#include "Binary_Mult.h"
#include "Binary_Plus.h"
#include "Binary_Minus.h"
#include "Constant.h"
#include "Unary_Minus.h"
#include "Unary_Plus.h"


int main() {
    // deux = 2
    Constant deux = 2;

    // trois = 3
    Constant trois = 3;

    // quatre = 4
    Constant quatre = 4;

    // cinq = 5
    Constant cinq = 5;

    // moins_cinq = -5
    Constant moins_cinq = -5;

    // zero = 0
    Constant zero = 0;


    // e00 = -3
    Unary_Minus moinsTrois(trois);

    Unary_Plus plusCinq(moins_cinq);

    // e1 = -3*3
    Binary_Mult e1(trois, moinsTrois);

    // e2 = -3/0
    Binary_Div e2(moinsTrois, zero);

    // e2plus3 = (2 + 3)
    Binary_Plus e2plus3(deux, trois);

    //
    Binary_Minus e2minus4(moinsTrois, quatre);

    // anExpr = (2 + 3)*4
    Binary_Mult anExpr(e2plus3, quatre);

    cout << "3 = " << trois.eval() << endl;
    cout << "-3 = " << moinsTrois.eval() << endl;
    cout << "-5 = " << plusCinq.eval() << endl;
    cout << " 3 * -3 = " << e1.eval() << endl;
    try {
        cout << " -3 / 0 = " << e2.eval() << endl;
    } catch (const exception &e) { cout << e.what() << endl; }

    cout << "-3 - 4 = "  << e2minus4.eval()  << endl;
    cout << "2 + 3 = "  << e2plus3.eval()  << endl;

    cout << "(2 + 3) * 4 = "  << anExpr.eval()  << endl;

}

/*
 * On est obligé de garder les pointeurs pour Binary_Expr et UnaryExpr puisque ce sont des données
 * membres et qu'elles ne peuvent pas être initialisées à la créatio ndes objets.
 * Tous les autres pointeurs peuvent être remplacés par des références.
 */