//
// Created by Florian Salord on 28/09/2018.
//
#ifndef CODE_PERSON_H
#define CODE_PERSON_H

#include <iostream>
#include <string>
#include <array>
#include <vector>

using std::string;
using std::ostream;
using std::vector;
using std::array;

enum class Gender {
    M, F
};

class Person {


public:
    Person *partner = nullptr;
    vector<Person *> parents;
    vector<Person *> enfants;
    Gender g;
    string name;

    Person(Gender gender, string name);

    void set_partner(Person &partner);

    void remove_partner();

    void add_parent(Person &parent);

    friend ostream &operator<<(ostream &os, const Person &p);
};


#endif //CODE_PERSON_H
