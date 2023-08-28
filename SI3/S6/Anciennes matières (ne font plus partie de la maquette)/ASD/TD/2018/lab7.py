# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Marc Gaetano

from graph import *


#
# NOTICE: the complexity of all functions must be
# O(|V| + |E|) where V is the set of all vertices
# and E the set of all edges for the given graph
#

def connected_components(g):
	"""
	Return a dictionary cc such that for each vertex v
	cc[v] is the number of the connected component v belongs to
	"""

	def mark_cc(g, v, ccn, cc):
		"""
		For v and all reachable vertices from v
		set the connected component number to ccn
		"""
		cc[v] = ccn
		for n in g.adjacents(v):
			if n not in cc:
				mark_cc(g, n, ccn, cc)
		return cc

	cc = dict()
	i = 1

	for v in g.vertices():
		if v not in cc:
			mark_cc(g, v, i, cc)
			i += 1

	return cc


def has_cycle(g):
	"""
	Return True if g is cyclic, False otherwise
	"""

	def cyclic_directed(g, v, marked, parent=None):
		"""
		Return True if directed graph g is cyclic, False otherwise
		"""
		marked[v] = 1
		for n in g.adjacents(v):
			if (n not in marked and cyclic_directed(g, n, marked)) or marked[n] == 1:
				return True
		marked[v] = 2
		return False

	def cyclic_undirected(g, v, marked, parent=None):
		marked[v] = 1
		for n in g.adjacents(v):
			if (n is not parent) and (n in marked or cyclic(g, n, marked, v)):
				return True
		return False

	if g.is_directed():
		cyclic = cyclic_directed
	else:
		cyclic = cyclic_undirected

	d = {}
	return any(v not in d and cyclic(g, v, d, None) for v in g.vertices())


def path(g, u, v):
	"""
	Return a path (a list of vertices) from vertex u to vertex v
	if such a path exists, [] otherwise
	"""

	def find_path(g, v, target, predecessor):
		"""
		Find a path from vertice v to vertice target and
		set the predecessor map if such a path exists.
		predecessor[y] = x means we move from x to y on the path
		"""
		predecessor[v] = None
		stack = [v]
		while stack:
			node = stack.pop()
			for n in g.adjacents(node):
				if n not in predecessor:
					stack.append(n)
					predecessor[n] = node
					if n is target:
						return True

		return False

	'''
	Return the path as a list [v1, v2, ..., v3] from
	the predecessor map and the starting vertex v
	'''
	predecessor = {}
	if not find_path(g, u, v, predecessor):
		return []
	res = [v]
	while predecessor[res[-1]]:
		res.append(predecessor[res[-1]])
	return list(reversed(res))


def has_root(g):
	"""
	Return a root of the directed graph g if such a root exists
	None otherwise
	"""
	first, marked = set(), set()

	def dfs(g, v, marked):
		"""
		Perform a dsf from vertex v and mark all reachable vertices
		marked is the set of marked vertices
		"""
		marked.add(v)
		stack = [v]
		while stack:
			node = stack.pop()
			for n in g.adjacents(node):
				if n not in marked:
					stack.append(n)
					marked.add(n)
				elif n in first and n is not v:
					first.remove(n)

	for n in g.vertices():
		if n not in marked:
			R = n
			first.add(n)
			dfs(g, n, marked)

	return R if len(first) == 1 else None


###
### TEST CODE
###

thegraphs = {"U1": graph_from_edgelist((
	('A', 'B'), ('C', 'D'), ('C', 'E'), ('D', 'E'), ('E', 'F'), ('G', 'H'), ('G', 'K'), ('H', 'I'), ('H', 'J'),
	('H', 'K'), ('I', 'J'))), "U2": graph_from_edgelist((
	('A', 'D'), ('A', 'E'), ('A', 'J'), ('B', 'C'), ('B', 'F'), ('B', 'G'), ('B', 'I'), ('C', 'F'), ('C', 'G'),
	('C', 'H'), ('D', 'E'), ('D', 'F'), ('G', 'H'))), "U3": graph_from_edgelist((
	('A', 'E'), ('B', 'D'), ('B', 'F'), ('B', 'H'), ('C', 'G'), ('G', 'I'), ('G', 'J'))), "U4": graph_from_edgelist((
	('A', 'C'), ('A', 'D'), ('B', 'E'), ('B', 'K'), ('C', 'E'), ('C', 'J'), ('D', 'F'), ('D', 'H'), ('E', 'G'),
	('E', 'I'))), "D1": graph_from_edgelist((
	('A', 'E'), ('B', 'D'), ('B', 'F'), ('C', 'E'), ('D', 'F'), ('F', 'C'), ('F', 'E'), ('G', 'A'), ('G', 'B'),
	('G', 'C')), True), "D2": graph_from_edgelist((
	('A', 'C'), ('A', 'E'), ('B', 'D'), ('D', 'F'), ('D', 'G'), ('E', 'C'), ('F', 'B')), True),
	"D3": graph_from_edgelist((
		('A', 'C'), ('B', 'D'), ('C', 'E'), ('C', 'G'), ('D', 'A'), ('D', 'F'), ('E', 'A'), ('F', 'B')), True),
	"D4": graph_from_edgelist((
		('B', 'E'), ('B', 'D'), ('C', 'D'), ('C', 'F'), ('D', 'E'), ('A', 'B'), ('A', 'C'), ('A', 'D'), ('D', 'F'),
		('D', 'G'), ('E', 'H'), ('E', 'G'), ('F', 'G'), ('F', 'I'), ('G', 'H'), ('G', 'I'), ('G', 'J'), ('H', 'J'),
		('I', 'J')), True)}


def display_available(thegraphs, directed=None):
	l = [g for g in thegraphs if
		 directed is None or directed and thegraphs[g].is_directed() or not directed and not thegraphs[g].is_directed()]
	print("Available graphs are:", sorted(l))
	return l


def choose_graph(directed=None):
	l = display_available(thegraphs, directed)
	while True:
		g = input("your choice: ").strip()
		if g in l:
			return thegraphs[g]


def build_graph():
	display_available(thegraphs)
	n = input("Enter the name of your graph: ").strip()
	d = int(input("The graph" + n + " is: undirected (0)  or  directed (1)? "))
	e = input("Enter the list of edges: ")
	thegraphs[n] = graph_from_edgelist(e, d)


while True:
	choice = input("exit(0) new graph(1) connected_components(2) has_cycle(3) path(4) has_root(5): ")
	if choice == "0":
		break
	elif choice == "1":
		build_graph()
	elif choice == "2":
		G = choose_graph(False)
		cc = connected_components(G)
		print(cc)
	elif choice == "3":
		G = choose_graph()
		if has_cycle(G):
			print("this graph is cyclic")
		else:
			print("this graph is acyclic")
	elif choice == "4":
		G = choose_graph()
		while True:
			start = G.get_vertex(input("enter the start vertex: ").strip())
			if start is not None:
				break
		while True:
			end = G.get_vertex(input("enter the end vertex: ").strip())
			if end is not None:
				break
		print(path(G, start, end))
	elif choice == "5":
		G = choose_graph(True)
		r = has_root(G)
		if r is None:
			print("no root")
		else:
			print(r, "is a root")
	else:
		print("wrong choice")
