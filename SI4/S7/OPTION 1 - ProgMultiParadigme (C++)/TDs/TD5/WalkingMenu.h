//
// Created by Florian Salord on 16/11/2018.
//

#ifndef CODE_WALKINGMENU_H
#define CODE_WALKINGMENU_H


#include "Menu.h"

class WalkingMenu : public Menu, public MenuItem {
    virtual void print(ostream &stream) const;

public:
    WalkingMenu(string, string);

    WalkingMenu(const WalkingMenu &);

    void execute();

    virtual WalkingMenu *clone() const;

    virtual ~WalkingMenu();
};


#endif //CODE_WALKINGMENU_H
