//
// Created by Florian Salord on 16/11/2018.
//

#include "SimpleMenuItem.h"

SimpleMenuItem::SimpleMenuItem(string s) : MenuItem(s) {

}

void SimpleMenuItem::execute() {
    cout << "********** Execution de " << this->text.c_str() << endl;
    system(this->text.c_str());
}

SimpleMenuItem *SimpleMenuItem::clone() const {
    return new SimpleMenuItem(*this);
}

SimpleMenuItem::~SimpleMenuItem() = default;

void SimpleMenuItem::print(ostream &os) const {
    os << this->text;
}
