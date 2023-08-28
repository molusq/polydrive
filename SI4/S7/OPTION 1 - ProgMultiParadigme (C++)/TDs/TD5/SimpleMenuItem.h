//
// Created by Florian Salord on 16/11/2018.
//

#ifndef CODE_SIMPLEMENUITEM_H
#define CODE_SIMPLEMENUITEM_H

#include <iostream>
#include "MenuItem.h"

class SimpleMenuItem : public MenuItem {
    virtual void print(ostream &stream) const;

public:
    SimpleMenuItem(string);

    void execute();

    virtual SimpleMenuItem *clone() const;

    virtual ~SimpleMenuItem();
};


#endif //CODE_SIMPLEMENUITEM_H
