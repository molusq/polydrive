# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Christophe Papazian
# modified by Marc Gaetano


def _left(n): return 2 * n + 1


def _right(n): return 2 * n + 2


def _parent(n): return (n - 1) // 2


class BinaryHeapContainer:

	def __init__(self, init_array=None, key=lambda x: -x):
		if init_array is None:
			init_array = []
		self._array = init_array
		self.key = key
		self._size = len(init_array)
		if self._array: self.buildHeap()

	def __getitem__(self, n):
		return self._array[n]

	def __setitem__(self, n, v):
		self._array[n] = v

	def __len__(self):
		return self._size

	def __bool__(self):
		return bool(self._array)

	def __repr__(self):
		return repr(self._array)

	def smaller(self, i, j):
		return i < self._size and j < self._size and self.key(self._array[i]) < self.key(self._array[j])

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
		for n in range(len(self) // 2 - 1, -1, -1): self.percolate_down(n)

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
			self._size = 0
			return self._array[0]
		self._size -= 1
		v, self[0] = self[0], self._array[self._size]
		self.percolate_down(0)
		return v
