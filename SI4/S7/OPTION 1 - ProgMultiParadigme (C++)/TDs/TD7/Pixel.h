//
// Created by Florian Salord on 14/12/2018.
//

#ifndef TD7_PIXEL_H
#define TD7_PIXEL_H

#include <iostream>

using std::istream;
using std::ostream;

class Pixel {
public:
    int R, G, B;

    friend istream &operator>>(istream &, Pixel &);

    friend ostream &operator<<(ostream &, Pixel &);

    Pixel(int, int, int);
};


#endif //TD7_PIXEL_H
