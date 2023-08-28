//
// Created by Florian Salord on 14/12/2018.
//

#include "Image.h"

istream &operator>>(istream &is, Image &im) {
    getline(is, im.format);
    getline(is, im.comment);
    is >> im.largeur >> im.hauteur >> im.depth;
    while (!is.eof()) {
        int r, g, b;
        is >> r >> g >> b;
        Pixel p(r, g, b);
        im.pixels.push_back(p);
    }
    return is;
}

Image::Image(string path) {
    std::ifstream f(path);
    f >> *this;
}

ostream &operator<<(ostream &os, Image &im) {
    os << "Image (" << im.largeur << ", " << im.hauteur << ")" << std::endl;
    for (Pixel &p : im.pixels) {
        os << p;
    }
    return os;
}
