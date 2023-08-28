# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Christophe Papazian

'''
IMPORTANT NOTICE
Don't use any import
You must complete all methods and functions with 'pass'
'''


class BST:
	'''
	Class for Binary Search Tree

	Attributes:
		root : root node or None if empty
	'''

	class Node:
		'''
		A Node in the Binary Search Tree
		While in the tree, you can use the method 'replace(node2)' to replace that node and all its subtree by node2 (or None)
		Use node[False] and node[True] to access or modify left and right sons
		'''

		def __init__(self, data):
			self.data, self.__left, self.__right = data, None, None

		def __getitem__(self, i):
			'''Accessors'''
			return self.__right if i else self.__left

		def __setitem__(self, i, v):
			'''Setters'''
			assert (v is None or isinstance(v, self.__class__))
			if self[i] is not None: del self[i].replace
			if i:
				self.__right = v
			else:
				self.__left = v
			if v is not None:
				def aux(x): self[i] = x

				v.replace = aux

		def __repr__(self):
			return 'Node %s %s%s' % (str(self.data), '<' if self.__left else '.', '>' if self.__right else '.')

	def __init__(self):
		self.__root = None

	@property
	def root(self):
		return self.__root

	@root.setter
	def root(self, v):
		assert (v is None or isinstance(v, self.__class__.Node))
		if self.__root is not None: del self.__root.replace
		self.__root = v
		if v is not None:
			def aux(x): self.root = x

			v.replace = aux

	def is_empty(self):
		return self.root is None

	def empty(self):
		self.root = None

	def contains(self, value):
		'''
		return True if value is in the BST, False otherwise
		'''
		node = self.root
		while node is not None:
			if value == node.data:
				return True
			node = node[value > node.data]
		return False

	def insert(self, value):
		'''
		return True and insert value in BST if value is not in the BST
		return False and do nothing otherwise
		'''
		if self.root is None:
			self.root = self.Node(value)
			return True

		node = self.root
		while True:
			if value == node.data:
				return False
			cond = value > node.data
			if node[cond] is None:
				node[cond] = self.Node(value)
				return True
			node = node[cond]

	def find_extremum(self, maxi=False):
		'''
		return the minimum (or maximum is maxi is True) value in the BST
		raise ValueError if the BST is empty
		'''
		if self.root is None:
			raise ValueError("find_extremum : empty BST")
		node = self.root
		while node[maxi] is not None:
			node = node[maxi]
		return node.data

	def remove_extremum(self, maxi=False, node=None):
		'''
		remove the minimum (or maximum if maxi is True) value in the BST and return that value
		start the search at self.root if node is None else at the specified node
		raise ValueError if the BST is empty
		'''
		if node is None:
			node = self.root
		if node is None:
			raise ValueError
		while node[maxi] is not None:
			node = node[maxi]
		node.replace(node[not maxi])
		return node.data  # car node n'est pas écrasé

	def remove(self, value):
		'''
		remove value from the BST
		return True if the value was removed, False if not found
		'''
		node = self.root
		while node is not None:
			if value == node.data:
				if node[0] is not None and node[1] is not None:
					node.data = self.remove_extremum(False, node[1])
				elif node[0] is None:
					node.replace(node[1])
				else:
					node.replace(node[0])
				return True
			node = node[value > node.data]
		return False

	def remove_compare(self, value, greater=False):
		'''
		remove all values v from the tree such that v<value (v>value if greater is True)
		'''
		if self.root is None:
			raise ValueError("remove_compare : empty BST")
		node = self.root
		compare = (lambda x, y: x > y) if greater else (lambda x, y: x < y)
		while node is not None:
			if compare(node.data, value):
				node.replace(node[not greater])
				node = node[not greater]
			else:
				node = node[greater]

	def __iter__(self):
		'''
		iter on all elements of the BST from the smallest to the largest
		'''

		def iter_from_node(node):
			if node:
				yield from iter_from_node(node[False])
				yield node.data
				yield from iter_from_node(node[True])

		return iter_from_node(self.root)

	### Method provided to print and test the trees, don't modify them

	def print(self):
		'''
		print the tree in a readable format. Use unicode box drawing characters.
		'''

		def aux(node, prev="", dataprev="", turn=None):
			if node[False] is not None or node[True] is not None:
				prevl = prev[:-2] + "  " if prev and turn is None or turn is False else prev
				if node[False] is not None: aux(node[False], prevl + '\u2503 ', prevl + '\u250F\u2578', False)
			print(dataprev + str(node.data))
			if node[False] is not None or node[True] is not None:
				prevr = prev[:-2] + "  " if prev and turn is None or turn is True else prev
				if node[True] is not None: aux(node[True], prevr + '\u2503 ', prevr + '\u2517\u2578', True)

		if self.root:
			aux(self.root)
		else:
			print("Empty")

	def compact_string(self):
		def aux(node):
			if node is not None:
				yield str(node.data)
				if node[False] or node[True]:
					yield ("(")
					yield from aux(node[False])
					yield ("|")
					yield from aux(node[True])
					yield (")")

		return ''.join(aux(self.root))


class RankedBST(BST):
	class Node(BST.Node):
		def __init__(self, data):
			super().__init__(data)
			self.sizeOfLeft = 0

	def insert(self, value):
		'''
		return True and insert value in BST if value is not in the BST
		return False and do nothing otherwise
		'''
		pass

	def remove_extremum(self, maxi=False, node=None):
		'''
		remove the minimum (or maximum if maxi is True) value in the BST and return that value
		start the search at self.root if node is None else at the specified node
		raise ValueError if the BST is empty
		'''
		pass

	def remove(self, value):
		'''
		remove value from the BST
		return True if the value was removed, False if not found
		'''
		pass

	def __getitem__(self, rank):
		'''
		rankedbst[i] must return the ith element of the tree (0 is the first)
		raise IndexError if it's out of bound
		'''
		pass

	def index(self, value):
		'''
		tree.index(value) must return the index of the value (0 if it's the minimum)
		raise ValueError if the value is not in the tree
		'''
		pass


####
#### Don't change anything under this comment, the class test is here to help you evaluate your work.
#### You will need the file big-file.txt in the same directory for the test on pairing.
####

import random
import unittest


def build_BST(s):
	l = s.split()
	res = BST()
	for i in l: res.insert(int(i))
	return res


def isnt_implemented(*args): return any(len(f.__code__.co_code) < 5 for f in args)


class TestLab3(unittest.TestCase):

	@unittest.skipIf(isnt_implemented(BST.insert), "BST.insert not implemented")
	def test1_insert(self):
		for j in range(1, 100):
			bst, s = BST(), set()
			for i in range(j):
				r = random.randint(-10, 10)
				self.assertEqual(r not in s, bst.insert(r))
				s.add(r)
		self.assertEqual("0(-1|1)", build_BST("0 -1 1").compact_string())
		self.assertEqual("8(-7(-9(-10|-8)|-4(-6(|-5)|3(2(-3(|1(-2(|-1(|0))|))|)|7(5(4|6)|))))|9(|10))", build_BST(
			'8 -7 -4 3 2 2 -4 -6 3 -9 -3 8 1 -2 -2 9 -10 1 7 3 -1 5 -10 7 7 -4 -2 -7 10 -6 -3 3 0 9 4 2 -9 -7 -5 -9 3 4 6 10 1 -3 10 -4 4 1 7 -7 -8 -6 -4 1 6 4 -3 3 -10 -2 -6 5 -2 0 1 9 -9 10 5 -2 9 3 4 10 -4 7 5 -1 -8 -6 6 -9 -3 6 0 9 -10 10 -10 -2 -4 -4 -2 9 3 8 5 -2').compact_string())

	@unittest.skipIf(isnt_implemented(BST.insert, BST.find_extremum), "BST.find_extremum not implemented")
	def test2_find_extremum(self):
		bst = BST()
		with self.assertRaises(ValueError):
			bst.find_extremum()
		for j in range(1, 100):
			bst = BST()
			mini, maxi = float("inf"), float("-inf")
			for i in range(j):
				r = random.randint(-20, 20)
				bst.insert(r)
				mini, maxi = min(mini, r), max(maxi, r)
			self.assertEqual(mini, bst.find_extremum(False))
			self.assertEqual(maxi, bst.find_extremum(True))

	@unittest.skipIf(isnt_implemented(BST.insert, BST.remove_extremum), "BST.remove_extremum not implemented")
	def test3_remove_extremum(self):
		bst = BST()
		with self.assertRaises(ValueError):
			bst.find_extremum()
		for j in range(1, 100):
			bst, s = BST(), set()
			for i in range(j):
				r = random.randint(-20, 20)
				bst.insert(r)
				s.add(r)
			M = max if j & 1 else min
			while (len(s) > 0):
				self.assertEqual(M(s), bst.remove_extremum(j & 1))
				s.remove(M(s))

	@unittest.skipIf(isnt_implemented(BST.insert, BST.remove), "BST.remove not implemented")
	def test4_remove(self):
		for j in range(1, 100):
			bst, s = BST(), set()
			for i in range(j):
				r = random.randint(-10, 10)
				bst.insert(r)
				s.add(r)
			for i in range(j):
				r = random.randint(-10, 10)
				self.assertEqual(r in s, bst.remove(r))
				s.discard(r)
		b = build_BST('8 -7 -9 -10 -8 -4 -6 -5 3 2 -3 1 -2 -1 0 7 5 4 6 9 10')
		b.remove(8)
		self.assertEqual('9(-7(-9(-10|-8)|-4(-6(|-5)|3(2(-3(|1(-2(|-1(|0))|))|)|7(5(4|6)|))))|10)', b.compact_string())
		b.remove(-7)
		self.assertEqual('9(-6(-9(-10|-8)|-4(-5|3(2(-3(|1(-2(|-1(|0))|))|)|7(5(4|6)|))))|10)', b.compact_string())
		b.remove(-3)
		self.assertEqual('9(-6(-9(-10|-8)|-4(-5|3(2(1(-2(|-1(|0))|)|)|7(5(4|6)|))))|10)', b.compact_string())
		b.remove(-10)
		self.assertEqual('9(-6(-9(|-8)|-4(-5|3(2(1(-2(|-1(|0))|)|)|7(5(4|6)|))))|10)', b.compact_string())

	@unittest.skipIf(isnt_implemented(BST.insert, BST.remove_compare), "BST.remove_compare not implemented")
	def test5_remove_compare(self):
		b = build_BST('8 -7 -9 -10 -8 -4 -6 -5 3 2 -3 1 -2 -1 0 7 5 4 6 9 10')
		b.remove_compare(-1)
		self.assertEqual('8(3(2(1(-1(|0)|)|)|7(5(4|6)|))|9(|10))', b.compact_string())
		b.remove_compare(3, True)
		self.assertEqual('3(2(1(-1(|0)|)|)|)', b.compact_string())

	@unittest.skipIf(isnt_implemented(BST.insert, BST.__iter__), "BST.__iter__ not implemented")
	def test6_iter(self):
		for j in range(1, 100):
			bst, s = BST(), set()
			for i in range(j):
				r = random.randint(-10, 10)
				bst.insert(r)
				s.add(r)
			self.assertEqual(list(sorted(s)), list(bst))

	@unittest.skipIf(isnt_implemented(RankedBST.insert, RankedBST.remove, RankedBST.__getitem__),
					 "BST.__iter__ not implemented")
	def test7_RankedBST_getitem(self):
		for j in range(1, 100):
			rbst, s = RankedBST(), set()
			for i in range(j):
				r = random.randint(-10, 10)
				rbst.insert(r)
				s.add(r)
			for k in range((len(s) - 1) // 2):
				r = random.choice(list(s))
				rbst.remove(r)
				s.remove(r)
			l = list(sorted(s))
			for i, v in enumerate(l): self.assertEqual(l[i], rbst[i])
			with self.assertRaises(IndexError):
				rbst[len(l)]

	@unittest.skipIf(isnt_implemented(RankedBST.insert, RankedBST.remove, RankedBST.index), "BST.index not implemented")
	def test8_RankedBST_index(self):
		for j in range(1, 100):
			rbst, s = RankedBST(), set()
			for i in range(j):
				r = random.randint(-10, 10)
				rbst.insert(r)
				s.add(r)
			for k in range((len(s) - 1) // 2):
				r = random.choice(list(s))
				rbst.remove(r)
				s.remove(r)
			l = list(sorted(s))
			for i, v in enumerate(l): self.assertEqual(i, rbst.index(v))
			with self.assertRaises(ValueError):
				rbst.index(100)


import sys

if __name__ == "__main__" and sys.flags.interactive == 0:
	unittest.main()
