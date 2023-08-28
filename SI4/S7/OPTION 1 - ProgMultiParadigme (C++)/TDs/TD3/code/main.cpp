#include <iostream>
#include "Node.h"
#include "Tree.h"
#include <vector>
#include <algorithm>
#include <random>
#include <typeinfo>

using std::cout;
using std::endl;

int main() {
    Node<char> n('a');
    Node<char> n1('b');
    Node<char> n2('c');
    Node<char> n3('d');
    Node<char> n4('e');
    Node<char> n5('f');
    Node<char> n6('g');

    unsigned seed = static_cast<unsigned int>(std::chrono::system_clock::now().time_since_epoch().count());
    auto rng = std::default_random_engine(seed);

    /*
    n.set_right_child(&n1);
    n.set_left_child(&n2);
    n2.set_left_child(&n3);
    n2.set_right_child(&n4);
    n4.set_right_child(&n5);
    cout << n << endl;

    cout << endl << "Iterate left : ";
    for (Node<char> *node : n.iterate_left_hand()) {
        cout << node->getValue() << " ";
    }
    cout << endl;
    */

    Tree<char> tree(n);
    vector<Node<char> *> vec;
    vec.push_back(&n1);
    vec.push_back(&n2);
    vec.push_back(&n3);
    vec.push_back(&n4);
    vec.push_back(&n5);
    vec.push_back(&n6);

    std::shuffle(std::begin(vec), std::end(vec), rng);

    for (Node<char> *node : vec) {
        tree.add_node(*node);
    }
    cout << "Tree of Node<char> : " << tree.root << endl;

    Node<Node<char> > nn(n);
    Node<Node<char> > nn1(n1);
    Node<Node<char> > nn2(n2);
    Node<Node<char> > nn3(n3);
    Node<Node<char> > nn4(n4);
    Node<Node<char> > nn5(n5);
    Tree<Node<char> > tree2(nn);

    vector<Node<Node<char> > *> vec2;
    vec2.push_back(&nn1);
    vec2.push_back(&nn2);
    vec2.push_back(&nn3);
    vec2.push_back(&nn4);
    vec2.push_back(&nn5);
    std::shuffle(std::begin(vec2), std::end(vec2), rng);
    for (Node<Node<char> > *node2 : vec2) {
        tree2.add_node(*node2);
    }
    cout << "Tree of Node<Node<char>> : " << tree2.root << endl;

    return 0;
}