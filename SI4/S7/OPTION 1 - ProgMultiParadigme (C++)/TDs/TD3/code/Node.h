//
// Created by Florian Salord on 05/10/2018.
//

#ifndef CODE_NODE_H
#define CODE_NODE_H

#include <vector>
#include <iostream>

using std::vector;
using std::ostream;

template<typename T>
class Node;

template <typename T>
ostream &operator<<(ostream &, const Node<T> &);


template <typename T>
class Node {
    T value;
    Node<T> *left_child = nullptr;
    Node<T> *right_child = nullptr;

public:
    explicit Node(T v, Node<T> *left = nullptr, Node<T> *right = nullptr);       //car valeurs par défaut

    Node(const Node<T> &n) = default;

    void setValue(T v);

    int getValue();

    Node *get_left_child();

    void set_left_child(Node<T> *);

    Node *get_right_child();

    void set_right_child(Node<T> *);

    void remove_all_children();

    vector<Node<T> *> iterate_left_hand();

    void iterate_tree(vector<Node<T> *> *vec, Node<T> *);

    bool no_child();

    friend ostream &operator<<<>(ostream &, const Node<T> &);
};

#include "Node.cpp"
#endif //CODE_NODE_H
