//
// Created by Florian Salord on 16/11/2018.
//
#include <iostream>
#include "Menu.h"
#include "SimpleMenuItem.h"
#include "WalkingMenu.h"

int main() {

    Menu menu("LE MENU");
    SimpleMenuItem simple_menu_item_1("emacs");
    SimpleMenuItem simple_menu_item_2("top");
    SimpleMenuItem simple_menu_item_3("nettop");
    SimpleMenuItem simple_menu_item_4("ls");

    WalkingMenu walking_menu("COMMUNICATIONS", "Communications");

    walking_menu.addItem(simple_menu_item_3);
    walking_menu.addItem(simple_menu_item_4);

    WalkingMenu walking_menu_2("SECOND", "Second");
    walking_menu.addItem(walking_menu_2);

    menu.addItem(simple_menu_item_1);
    menu.addItem(simple_menu_item_2);
    menu.addItem(walking_menu);

    menu.activate();

}