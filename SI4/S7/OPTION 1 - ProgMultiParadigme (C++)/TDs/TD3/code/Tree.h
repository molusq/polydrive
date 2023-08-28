//
// Created by Florian Salord on 12/10/2018.
//

#ifndef CODE_TREE_H
#define CODE_TREE_H

#include <vector>
#include "Node.h"

template <typename T>
class Tree {
public:
    Node<T> root;

    void add_node(Node<T> &);

    void add_to_tree(Node<T> &, Node<T> &);

    explicit Tree(Node<T> &n);
};

#include "Tree.cpp"
#endif //CODE_TREE_H
