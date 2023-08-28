//
// Created by Florian Salord on 16/11/2018.
//

#include <iostream>
#include "MenuItem.h"

MenuItem::MenuItem(string s) {
    this->text = s;
}

ostream &operator<<(ostream &os, const MenuItem &mi) {
    mi.print(os);
    return os;
}

MenuItem::~MenuItem() = default;
