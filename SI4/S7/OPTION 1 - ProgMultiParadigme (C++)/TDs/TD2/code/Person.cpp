//
// Created by Florian Salord on 28/09/2018.
//

#include "Person.h"

using std::ostream;
using std::cout;

Person::Person(const Gender gender, const string name) {
    this->g = gender;
    this->name = name;
}

ostream &operator<<(ostream &os, const Person &p) {
    string s = (p.g == Gender::M) ? "Male" : "Female";
    os << p.name << ", " << s;
    for (Person *parent : p.parents) {
        os << ", " << parent->name;
    }
    return os;
}

void Person::set_partner(Person &partner) {
    this->partner = &partner;
    partner.partner = this;
}

void Person::remove_partner() {
    if(this->partner != nullptr) {
        this->partner->partner = nullptr;
    }
    this->partner = nullptr;
}

void Person::add_parent(Person &parent) {
    if (parents.size() == 2) {
        cout << "Too many parents" << std::endl;
        return;
    }
    this->parents.push_back(&parent);
    parent.enfants.push_back(this);
}