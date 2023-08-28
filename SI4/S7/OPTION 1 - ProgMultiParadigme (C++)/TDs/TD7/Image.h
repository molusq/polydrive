//
// Created by Florian Salord on 14/12/2018.
//

#ifndef TD7_IMAGE_H
#define TD7_IMAGE_H

#include <iostream>
#include "Pixel.h"
#include <string.h>
#include <vector>
#include <fstream>

using std::istream;
using std::ostream;
using std::string;
using std::vector;

class Image {
public:
    string format;
    string comment;
    int largeur;
    int hauteur;
    int depth;
    vector<Pixel> pixels;

    friend istream &operator>>(istream &, Image &);
    friend ostream &operator<<(ostream &, Image &);
    Image(string path);
};


#endif //TD7_IMAGE_H
