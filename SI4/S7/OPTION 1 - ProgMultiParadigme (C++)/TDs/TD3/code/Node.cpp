//
// Created by Florian Salord on 05/10/2018.
//

#include "Node.h"
#include <string>

using std::string;

template<typename T>
Node<T>::Node(T v, Node<T> *left, Node<T> *right)
        : value(v), left_child(left), right_child(right) {};

template<typename T>
void Node<T>::setValue(T v) {
    this->value = v;
}

template<typename T>
int Node<T>::getValue() {
    return this->value;
}

template<typename T>
Node<T> *Node<T>::get_left_child() {
    return this->left_child;
}

template<typename T>
void Node<T>::set_left_child(Node<T> *n) {
    this->left_child = n;
}

template<typename T>
Node<T> *Node<T>::get_right_child() {
    return this->right_child;
}

template<typename T>
void Node<T>::set_right_child(Node<T> *n) {
    this->right_child = n;
}


template<typename T>
bool Node<T>::no_child() {
    return get_left_child() == nullptr && get_right_child() == nullptr;
}

template<typename T>
void Node<T>::remove_all_children() {
    set_left_child(nullptr);
    set_right_child(nullptr);
}

template<typename T>
ostream &operator<<(ostream &os, const Node<T> &n) {
    os << "(" << n.value;

    if (n.left_child != nullptr) {
        os << "," << *n.left_child;
    }
    if (n.right_child != nullptr) {
        os << "," << *n.right_child;
    }
    os << ")";

    return os;
}

template<typename T>
vector<Node<T> *> Node<T>::iterate_left_hand() {
    vector<Node<T> *> vector1;
    iterate_tree(&vector1, this);
    return vector1;
}

template<typename T>
void Node<T>::iterate_tree(vector<Node<T> *> *vec, Node<T> *n) {
    vec->push_back(n);
    if (n->get_left_child() != nullptr) {
        iterate_tree(vec, n->get_left_child());
    }
    if (n->get_right_child() != nullptr) {
        iterate_tree(vec, n->get_right_child());
    }
}