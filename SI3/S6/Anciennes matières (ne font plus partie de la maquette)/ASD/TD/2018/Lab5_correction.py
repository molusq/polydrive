# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Christophe Papazian

'''
IMPORTANT NOTICE
Don't use any import
You must complete all methods and functions with 'pass'
'''


def _left(n): return 2 * n + 1


def _right(n): return 2 * n + 2


def _parent(n): return (n - 1) // 2


class BinaryHeap:

	def __init__(self, init_array=None, key=lambda x: x):
		if init_array is None:
			init_array = []
		self._array = list(init_array)
		self.key = key
		if self._array: self.buildHeap()

	def __getitem__(self, n):
		return self._array[n]

	def __setitem__(self, n, v):
		self._array[n] = v

	def __len__(self):
		return len(self._array)

	def __bool__(self):
		return bool(self._array)

	def __repr__(self):
		return repr(self._array)

	def smaller(self, i, j):
		return i < len(self._array) and j < len(self._array) and self.key(self._array[i]) < self.key(self._array[j])

	def percolate_down(self, n):
		'''
		move the element at index n down in the tree at the right place.
		'''
		while True:
			if self.smaller(_left(n), n) and not self.smaller(_right(n), _left(n)):
				nxt = _left(n)
			elif self.smaller(_right(n), n):
				nxt = _right(n)
			else:
				return n
			self[n], self[nxt], n = self[nxt], self[n], nxt

	def percolate_up(self, n):
		'''
		move the element at index n up in the tree at the right place.
		'''
		while n > 0:
			if self.smaller(n, _parent(n)):
				self[n], self[_parent(n)], n = self[_parent(n)], self[n], _parent(n)
			else:
				return n

	def buildHeap(self):
		'''
		heapify the list. Complexity is O(n)
		'''
		for n in range(len(self) // 2 - 1, -1, -1):
			self.percolate_down(n)

	def peek(self):
		'''
		return the extremum element.
		'''
		return self[0]

	def pop(self):
		'''
		return the extremum element and remove it from the heap.
		'''
		if len(self) == 0:
			raise ValueError("BinaryHeap.pop on empty heap")
		if len(self) == 1:
			return self._array.pop()
		v, self[0] = self[0], self._array.pop()
		self.percolate_down(0)
		return v

	def add(self, v):
		'''
		add the element v on the heap.
		'''
		self._array.append(v)
		self.percolate_up(len(self) - 1)

	def delete(self, v):
		'''
		delete the first element v in the heap.
		'''
		for i in range(len(self)):
			if self.key(self._array[i]) == self.key(v):
				if i == len(self) - 1:
					self._array.pop()
				self[i] = self._array.pop()
				self.percolate_down(self.percolate_up(i))
				return

	def delete_all(self, v):
		'''
		delete all the elements with key equal to v in the heap.
		'''
		self._array = [c for c in self._array if self.key(c) != self.key(v)]
		self.buildHeap()


class DynamicMedian:
	def __init__(self):
		self.mini, self.maxi = BinaryHeap(key=lambda x: -x), BinaryHeap()

	def __len__(self):
		return len(self.mini) + len(self.maxi)

	def add(self, v):
		if not self.maxi or v >= self.maxi.peek():
			self.maxi.add(v)
		else:
			self.mini.add(v)
		if len(self.maxi) > len(self.mini) + 1:
			self.mini.add(self.maxi.pop())
		elif len(self.maxi) < len(self.mini):
			self.maxi.add(self.mini.pop())

	def median(self):
		return self.maxi[0]

	def pop_median(self):
		res = self.maxi.pop()
		if len(self.maxi) < len(self.mini): self.maxi.add(self.mini.pop())
		return res


class DHeap(BinaryHeap):

	def __init__(self, D, init_array=[], key=lambda x: x):
		self.D = D
		self._array = list(init_array)
		self.key = key
		if self._array: self.buildHeap()

	def percolate_down(self, n):
		while True:
			mini = min(range(min(D, len(self) - self.D * n)), key=lambda i: self[self.D * n + i], default=None)
			if self.smaller(mini, n):
				self[n], self[mini], n = self[mini], self[n], mini
			else:
				return

	def percolate_up(self, n):
		while n > 0:
			parent = (n - 1) // self.D
			if self.smaller(n, parent):
				self[n], self[parent], n = self[parent], self[n], parent
			else:
				return

	def buildHeap(self):
		for n in range((len(self) + self.D - 2) // self.D - 1, -1, -1): self.percolate_down(n)


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


class TestLab5(unittest.TestCase):

	@unittest.skipIf(isnt_implemented(BinaryHeap.percolate_down), "BinaryHeap.percolate_down not implemented")
	def test1_percolate_down(self):
		for i, t, r in [(0, [0], [0]), (0, [1, 0], [0, 1]), (0, [10, 2, 4, 7, 8, 9, 15], [2, 7, 4, 10, 8, 9, 15])]:
			b = BinaryHeap()
			b._array = list(t)
			b.percolate_down(i)
			self.assertEqual(r, b._array)

	@unittest.skipIf(isnt_implemented(BinaryHeap.percolate_up), "BinaryHeap.percolate_up not implemented")
	def test2_percolate_up(self):
		for i, t, r in [(0, [0], [0]), (1, [1, 0], [0, 1]), (6, [2, 4, 7, 8, 9, 15, 0], [0, 4, 2, 8, 9, 15, 7])]:
			b = BinaryHeap()
			b._array = list(t)
			b.percolate_up(i)
			self.assertEqual(r, b._array)

	@unittest.skipIf(isnt_implemented(BinaryHeap.buildHeap), "BinaryHeap.buildHeap not implemented")
	def test3_buildHeap(self):
		for t, r in [([0], [0]), ([1, 0], [0, 1]), ([2, 4, 7, 8, 9, 15, 0], [0, 4, 2, 8, 9, 15, 7]),
					 ([12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], [0, 2, 1, 4, 3, 7, 6, 5, 9, 11, 8, 10, 12])]:
			b = BinaryHeap(t)
			self.assertEqual(r, b._array)

	@unittest.skipIf(isnt_implemented(BinaryHeap.peek), "BinaryHeap.peek not implemented")
	def test4_peek(self):
		for i in range(1, 100):
			r = [random.randint(0, i) for _ in range(i)]
			b = BinaryHeap(r)
			self.assertEqual(min(r), b.peek())

	@unittest.skipIf(isnt_implemented(BinaryHeap.pop), "BinaryHeap.pop not implemented")
	def test5_pop(self):
		for i in range(1, 100):
			r = [random.randint(0, i) for _ in range(i)]
			b = BinaryHeap(r)
			for _ in range(len(r)):
				self.assertEqual(min(r), b.pop())
				r.remove(min(r))

	@unittest.skipIf(isnt_implemented(BinaryHeap.add), "BinaryHeap.add not implemented")
	def test6_add(self):
		for i in range(1, 100):
			r = [random.randint(0, i * i) for _ in range(i)]
			b = BinaryHeap(r)
			for _ in range(len(r)):
				self.assertEqual(min(r), b.pop())
				r.remove(min(r))
				nr = random.randint(0, i * i)
				b.add(nr)
				r.append(nr)

	@unittest.skipIf(isnt_implemented(DynamicMedian.pop_median), "DynamicMedian.pop_median not implemented")
	def test7_median(self):
		for i in range(1, 20):
			dm = DynamicMedian()
			witness = []
			for j in range(i * i):
				for k in range(3):
					r = random.randint(0, i * i)
					dm.add(r)
					witness.append(r)
				witness.sort()
				md = dm.pop_median()
				self.assertEqual(witness[len(witness) // 2], md)
				witness.remove(md)


import sys

if __name__ == "__main__" and sys.flags.interactive == 0:
	unittest.main()
