# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Christophe Papazian

'''
IMPORTANT NOTICE
Don't use any import
You must complete all methods and functions with 'pass'
'''


class Tree:
    '''
    Class for Binary Tree

    Attributes:
        data                 : what the node contains
        left  (Tree or None) : left son
        right (Tree or None) : right son
    '''

    def __init__(self, data=None, left=None, right=None):
        self.data, self.left, self.right = data, left, right

    @property
    def sons(self):
        return self.left, self.right

    def is_leaf(self):
        return (self.left is None) and (self.right is None)

    def __getitem__(self, i):
        '''Accessors'''
        return self.right if i else self.left

    def print(self, prev="", dataprev=""):
        '''
        print the tree in a readable format. Use unicode box drawing characters.
        '''
        print(dataprev + self.data)
        if self.left is not None or self.right is not None:
            nprev = prev + '\u2503 '
            # print(nprev)
            if self.left is not None:
                self.left.print(nprev, prev + '\u2523\u2578')
            else:
                print(prev + '\u2523\u2578')
            # print(nprev)
            nprev = prev + '  '
            if self.right is not None:
                self.right.print(nprev, prev + '\u2517\u2578')
            else:
                print(prev + '\u2517\u2578')
    # print(prev)


def height(tree):
    '''
    compute the height of a tree
    '''
    return -1 if tree is None else 1 + max(height(son) for son in tree.sons)


def lowness(tree):
    '''
    compute the lowness of a tree
    '''
    return -1 if tree is None else 1 + min((lowness(son) for son in tree.sons if son is not None), default=-1)


def size(tree):
    '''
    compute the size of a tree (number of nodes)
    '''
    return 0 if tree is None else 1 + sum(size(son) for son in tree.sons)


def leaves(tree):
    '''
    compute the number of leaves of a tree
    '''
    return 0 if tree is None else max(1, sum(leaves(son) for son in tree.sons))


def isomorphic(t1, t2):
    '''
    return a bool
    True if the two trees are isomorphic (same structure, data is irrelevant)
    '''
    if t1 is None or t2 is None: return t1 == t2
    return isomorphic(t1.left, t2.left) and isomorphic(t1.right, t2.right)


def balanced1(tree):
    '''
    return a bool
    True if the tree is balanced. Use height and is in O(n**2)
    '''
    if tree is None:
        return True
    return abs(height(tree.left) - height(tree.right)) < 2 and balanced1(tree.left) and balanced1(tree.right)


def balanced2(tree):
    '''
    return a bool
    True if the tree is balanced. Don't use height and is in O(n)
    '''
    def aux(t):
        if t is None: return -1
        hl, hr = aux(t.left), aux(t.right)
        return None if hl is None or hr is None or abs(hl - hr) > 1 else 1 + max(hl, hr)

    return aux(tree) is not None


def shapely1(tree):
    '''
    return a bool
    True if the tree is shapely. Use height and lowness and is in O(n**2)
    '''
    if tree is None:
        return True
    return height(tree) <= lowness(tree) * 2 and shapely1(tree.left) and shapely1(tree.right)


def shapely2(tree):
    '''
    return a bool
    True if the tree is shapely. Don't use height and lowness and is in O(n)
    '''

    def aux(t):
        if t is None: return (-1, -1)
        lf, ri = aux(t.left), aux(t.right)
        if lf is None or ri is None: return
        h, l = 1 + max(lf[0], ri[0]), 1 + min(lf[1], ri[1])
        return (h, l) if h <= 2 * l else None

    return aux(tree) is not None


class ExpressionTree(Tree):
    def eval(self):
        '''
        return an int as a result of evaluating the expression in the tree
        '''
        if self.data in ['+', '-', '*', '/', '^']:
            return {'+': (lambda x, y: x + y),
                    '-': (lambda x, y: x - y),
                    '*': (lambda x, y: x * y),
                    '/': (lambda x, y: x // y),
                    '^': (lambda x, y: x ** y)}[self.data](self.left.eval(), self.right.eval())
        return int(self.data)


class GenealogyTree(Tree):
    def ancestors(self, n, male=False):
        '''
        returns the list of ancestors of the person at the root of the genealogy tree at a given level
        if male is True, only return the males at that level
        '''

        def aux(n, l):
            if n <= 0: return l
            return aux(n - 1, [(s, a) for _, t in l for s, a in ((True, t.left), (False, t.right)) if a is not None])

        return [a.data for s, a in aux(n, [(True, self)]) if s or not male]


####
#### Don't change anything under this comment, the class test is here to help you evaluate your work.
#### You will need the file big-file.txt in the same directory for the test on pairing.
####

import time, math


def timerfunc(N):
    def tf(func):
        """
        A timer decorator
        """

        def function_timer(*args, **kwargs):
            """
            A nested function for timing other functions
            """
            timeres = []
            for i in range(N):
                start = time.time()
                value = func(*args, **kwargs)
                end = time.time()
                timeres.append(end - start)
            timeres.remove(min(timeres))
            timeres.remove(max(timeres))
            runtime = math.fsum(timeres) / len(timeres)
            print("\nThe runtime for %s took %g milliseconds to complete" % (func.__name__, runtime * 1000))
            return value

        return function_timer

    return tf


import unittest


def build_tree(s, build=Tree):
    l = s.split()

    def aux(i):
        if i >= len(l) or l[i] == '!': return i + 1, None
        tag = l[i]
        i, left = aux(i + 1)
        i, right = aux(i)
        return i, build(tag, left, right)

    return aux(0)[1]


def complete_tree(n):
    if n >= 0:
        s = complete_tree(n - 1)
        return Tree('T', s, s)


class TestLab2(unittest.TestCase):

    def testA_lowness(self):
        self.assertEqual(lowness(build_tree("A")), 0)
        self.assertEqual(lowness(build_tree("A B C")), 0)
        self.assertEqual(lowness(build_tree("A B ! ! C")), 1)
        self.assertEqual(lowness(build_tree("A B C ! ! ! D E ! ! F")), 1)

    def testB_size(self):
        self.assertEqual(size(build_tree("A")), 1)
        self.assertEqual(size(build_tree("A B C")), 3)
        self.assertEqual(size(build_tree("A B ! ! C")), 3)
        self.assertEqual(size(build_tree("A B C ! ! ! D E ! ! F")), 6)

    def testC_leaves(self):
        self.assertEqual(leaves(build_tree("A")), 1)
        self.assertEqual(leaves(build_tree("A B C")), 1)
        self.assertEqual(leaves(build_tree("A B ! ! C")), 2)
        self.assertEqual(leaves(build_tree("A B C ! ! ! D E ! ! F")), 3)

    def testD_isomorphic(self):
        self.assertTrue(isomorphic(build_tree("A"), build_tree("B")))
        self.assertFalse(isomorphic(build_tree("A ! B"), build_tree("B A")))

    CT10 = complete_tree(10)
    CT8 = complete_tree(8)
    NCT = Tree('T', Tree('L', CT8, None), Tree('R', None, CT8))
    NST = build_tree("T L L ! ! ! R")

    @timerfunc(15)
    def testE_balanced1(self):
        self.assertTrue(balanced1(self.CT10))
        self.assertFalse(balanced1(self.NCT))
        self.assertTrue(balanced1(self.NST))

    @timerfunc(15)
    def testF_balanced2(self):
        self.assertTrue(balanced2(self.CT10))
        self.assertFalse(balanced2(self.NCT))
        self.assertTrue(balanced2(self.NST))

    @timerfunc(15)
    def testG_shapely1(self):
        self.assertTrue(shapely1(self.CT10))
        self.assertFalse(shapely1(self.NCT))
        self.assertFalse(shapely1(self.NST))

    @timerfunc(15)
    def testH_shapely2(self):
        self.assertTrue(shapely2(self.CT10))
        self.assertFalse(shapely2(self.NCT))
        self.assertFalse(shapely2(self.NST))

    def testI_ExpressionTree(self):
        self.assertEqual(build_tree("* + 3 ! ! 7 ! ! - 12 ! ! ^ 3 ! ! 2", ExpressionTree).eval(), 30)
        self.assertEqual(build_tree("* + / 13 ! ! 2 ! ! 7 ! ! - 12 ! ! ^ 3 ! ! 2", ExpressionTree).eval(), 39)

    GT = build_tree(
        "Edward David Carl ! Barbara Anthony ! ! Anna ! ! ! Dorothy Craig Bruce Allan ! ! Amanda ! ! ! Carol Brian Andrew ! ! Ann ! ! Brenda Albert ! ! Alice",
        GenealogyTree)

    def testJ_GenealogyTree(self):
        self.assertEqual(self.GT.ancestors(2), ['Carl', 'Craig', 'Carol'])
        self.assertEqual(self.GT.ancestors(2, male=True), ['Carl', 'Craig'])
        self.assertEqual(self.GT.ancestors(4),
                         ['Anthony', 'Anna', 'Allan', 'Amanda', 'Andrew', 'Ann', 'Albert', 'Alice'])
        self.assertEqual(self.GT.ancestors(4, male=True), ['Anthony', 'Allan', 'Andrew', 'Albert'])


import sys

if __name__ == "__main__" and sys.flags.interactive == 0:
    unittest.main()
