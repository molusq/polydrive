//
// Created by Florian Salord on 12/10/2018.
//

#include "Tree.h"
#include <random>

template<typename T>
void Tree<T>::add_node(Node<T> &node) {
    add_to_tree(root, node);
}

template<typename T>
void Tree<T>::add_to_tree(Node<T> &old_node, Node<T> &new_node) {
    srand(time(NULL));
    if (old_node.no_child()) {
        std::rand() % 2 == 0 ? old_node.set_left_child(&new_node) : old_node.set_right_child(&new_node);;
    } else if (old_node.get_left_child() == nullptr) {
        old_node.set_left_child(&new_node);
    } else if (old_node.get_right_child() == nullptr) {
        old_node.set_right_child(&new_node);
    } else if (old_node.get_left_child() != nullptr && old_node.get_right_child() != nullptr) {
        std::rand() % 2 == 0 ? add_to_tree(*old_node.get_left_child(), new_node) : add_to_tree(
                *old_node.get_right_child(), new_node);
    }
}

template<typename T>
Tree<T>::Tree(Node<T> &n):root(n) {
}