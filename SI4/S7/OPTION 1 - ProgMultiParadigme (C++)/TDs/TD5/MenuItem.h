//
// Created by Florian Salord on 16/11/2018.
//

#ifndef CODE_MENUITEM_H
#define CODE_MENUITEM_H

#include <string>

using namespace std;

class MenuItem {
    virtual void print(ostream &stream) const = 0;

public:
    string text;

    MenuItem(string);

    virtual void execute() = 0;

    virtual MenuItem *clone() const = 0;

    virtual ~MenuItem();

    friend ostream &operator<<(ostream &, const MenuItem &);
};


#endif //CODE_MENUITEM_H
