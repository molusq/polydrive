# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Marc Gaetano


class Partition:
	"""
	Union-find structure for maintaining disjoint sets
	"""

	# ------------------------- nested Node class -------------------------
	class Node:
		__slots__ = '_partition', '_value', '_size', '_parent'

		def __init__(self, partition, e):
			"""
			Create a new node that is the leader of its group
			"""
			self._partition = partition  # reference to Partition instance
			self._value = e
			self._size = 1
			self._parent = self  # to detect a group leader

		def value(self):
			"""
			Return value stored at this node
			"""
			return self._value

		def is_leader(self):
			return self._parent == self

	# ------------------------- private utility -------------------------
	def _validate(self, p):
		"""
		Check if p is a Node belonging to this container.
		Raise an exception otherwise
		"""
		if not isinstance(p, self.Node):
			raise TypeError('p must be proper Node type')
		if p._partition is not self:
			raise ValueError('p does not belong to this container')

	# ------------------------- public Partition methods -------------------------
	def __init__(self):
		self._count = 0

	def __len__(self):
		return self._count

	def create_node(self, e):
		"""
		Creates a new node containing value e, and returns it
		"""
		self._count += 1
		return self.Node(self, e)

	def find(self, p):
		"""
		Returns the node leader of the group containing node p.
		Performs path compression on the path from p to the leader
		"""
		pass

	def union(self, p, q):
		"""
		Merges the groups of leaders p and q (p and q must be distinct)
		"""
		pass
