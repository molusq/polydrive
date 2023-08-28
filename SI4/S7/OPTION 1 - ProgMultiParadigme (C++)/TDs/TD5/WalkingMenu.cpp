//
// Created by Florian Salord on 16/11/2018.
//

#include <iostream>
#include "WalkingMenu.h"

WalkingMenu::WalkingMenu(string s, string s2) : Menu(s), MenuItem(s2) {

}

void WalkingMenu::execute() {
    Menu::activate();
}

WalkingMenu *WalkingMenu::clone() const {
    return new WalkingMenu(*this);
}

WalkingMenu::WalkingMenu(const WalkingMenu &wm) : Menu(wm.title), MenuItem(wm.text) {
    for (MenuItem *mit : wm.items) {
        this->items.push_back(mit->clone());
    }
}

WalkingMenu::~WalkingMenu() = default;

void WalkingMenu::print(ostream &os) const {
    os << this->text << " ->";
}
