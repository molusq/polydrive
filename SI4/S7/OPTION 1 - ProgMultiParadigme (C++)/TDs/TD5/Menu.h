//
// Created by Florian Salord on 16/11/2018.
//

#ifndef CODE_MENU_H
#define CODE_MENU_H

#include <string>
#include <vector>
#include "MenuItem.h"

using namespace std;

class Menu {

public:
    string title;
    vector<MenuItem *> items;

    Menu(string);

    Menu(const Menu &);

    void activate();

    void addItem(MenuItem &);

    virtual ~Menu();
};


#endif //CODE_MENU_H
