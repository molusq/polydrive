#include <iostream>
#include "Person.h"
#include <assert.h>

using std::cout;
using std::endl;

void check_parents(Person &p) {
    assert(p.parents.at(0) != nullptr);
    assert(p.parents.at(1) != nullptr);
}

int main() {
    Person charles(Gender::M, "Charles");
    Person amandine(Gender::F, "Amandine");
    amandine.set_partner(charles);
    assert(amandine.partner == &charles);
    assert(charles.partner == &amandine);

    Person fred(Gender::M, "Fred");
    fred.add_parent(charles);
    fred.add_parent(amandine);
    check_parents(fred);

    amandine.remove_partner();

    assert(amandine.partner == nullptr);
    assert(charles.partner == nullptr);

    Person sylvie(Gender::F, "Sylvie");
    charles.set_partner(sylvie);
    assert(sylvie.partner == &charles);
    assert(charles.partner == &sylvie);

    Person regis(Gender::M, "Regis");
    regis.add_parent(charles);
    regis.add_parent(sylvie);

    Person jeff(Gender::M, "Jeff");
    jeff.set_partner(amandine);
    assert(jeff.partner == &amandine);
    assert(amandine.partner == &jeff);

    Person marie(Gender::F, "Marie");
    marie.add_parent(charles);
    marie.add_parent(sylvie);

    Person pedro(Gender::M, "Pedro");
    pedro.add_parent(charles);
    pedro.add_parent(sylvie);

    marie.set_partner(regis);
    assert(marie.partner == &regis);
    assert(regis.partner == &marie);

    Person benoit(Gender::M, "Benoit");
    benoit.add_parent(marie);
    benoit.add_parent(regis);

    check_parents(fred);
    check_parents(regis);
    check_parents(marie);
    check_parents(pedro);
    check_parents(benoit);


    cout << "All good" << endl;

    return 0;
}