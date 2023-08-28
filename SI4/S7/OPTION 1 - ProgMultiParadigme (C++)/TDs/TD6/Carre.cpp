

#include <OpenGL/OpenGL.h>
#include <OpenGL/glu.h>
#include "Carre.h"

void Carre::draw() {
    glColor3ub(color->r, color->g, color->b); //face rouge
    glVertex3d(point1->x, point1->y, point1->z);
    glVertex3d(point2->x, point2->y, point2->z);
    glVertex3d(point3->x, point3->y, point3->z);
    glVertex3d(point4->x, point4->y, point4->z);
}

Carre::Carre(Color &c, Point &p1, Point &p2, Point &p3, Point &p4) {
    this->color = &c;
    this->point1 = &p1;
    this->point2 = &p2;
    this->point3 = &p3;
    this->point4 = &p4;

}
