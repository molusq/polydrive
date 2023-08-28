//
// Created by Florian Salord on 16/11/2018.
//

#include <iostream>
#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include "Menu.h"
#include "MenuItem.h"

using namespace std;

void Menu::addItem(MenuItem &mit) {
    this->items.push_back(mit.clone());
}

void Menu::activate() {
    unsigned int selection;
    while (true) {
        int i = 0;
        cout << this->title << endl;
        for (MenuItem *mit: items) {
            cout << i++ << " - " << (*mit) << endl;
        }
        cout << i << " - " << "exit" << endl;
        cout << "Votre choix ? ";
        cin >> selection;
        if (selection == this->items.size()) {
            return;
        }
        this->items.at(selection)->execute();
    }
}

Menu::Menu(string s) {
    this->title = s;
}

Menu::Menu(const Menu &menu) {
    this->title = menu.title;
    for (MenuItem *mit : menu.items) {
        this->items.push_back(mit);
    }
}

Menu::~Menu() {
    for (MenuItem *mit : this->items) {
        delete mit;
    }
    this->items.clear();
}