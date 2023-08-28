#ifndef _CARRE_H_
#define _CARRE_H_


#include "Color.h"
#include "Point.h"

class Carre {
public:
    Color *color;
    Point *point1;
    Point *point2;
    Point *point3;
    Point *point4;

    Carre(Color &, Point &, Point &, Point &, Point &);

    void draw();
};

#endif