//
// Created by Florian Salord on 14/12/2018.
//

#include "Pixel.h"

istream &operator>>(istream &is, Pixel &pi) {
    is >> pi.R >> pi.G >> pi.B;
    return is;
}

Pixel::Pixel(int red, int green, int blue) {
    this->R = red;
    this->G = green;
    this->B = blue;
}

ostream &operator<<(ostream &os, Pixel &p) {
    return os << "pixel(" << p.R << ", " << p.G << ", " << p.B << ")" << std::endl;
}
