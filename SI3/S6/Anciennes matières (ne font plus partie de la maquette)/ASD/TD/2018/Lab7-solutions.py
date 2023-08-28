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

def connected_components(G):
	'''
Return a dictionary cc such that for each vertex v
cc[v] is the number of the connected component v belongs to
	'''

	def mark_cc(G, v, ccn, cc):
		'''
	For v and all reachable vertices from v
	set the connected component number to ccn
		'''
		cc[v] = ccn
		for a in G.adjacents(v):
			if a not in cc:
				mark_cc(G, a, ccn, cc)

	ccn = 1
	cc = {}
	for v in G.vertices():
		if v not in cc:
			mark_cc(G, v, ccn, cc)
			ccn += 1
	return cc


def has_cycle(G):
	'''
Return True if G is cyclic, False otherwise
	'''

	def cyclic_directed(G, v, marked, parent=None):
		'''
	Return True if directed graph G is cyclic, False otherwise
		'''
		marked[v] = 1
		for a in G.adjacents(v):
			if a not in marked:
				if cyclic(G, a, marked):
					return True
			elif marked[a] == 1:
				return True
		marked[v] = 2
		return False

	def cyclic_undirected(G, v, marked, parent=None):
		'''
	Return True if undirected graph G is cyclic, False otherwise
		'''
		marked[v] = 1
		for a in G.adjacents(v):
			if a not in marked:
				if cyclic(G, a, marked, v):
					return True
			elif a != parent:
				return True
		return False

	if G.is_directed():
		cyclic = cyclic_directed
	else:
		cyclic = cyclic_undirected

	marked = {}
	for v in G.vertices():
		if v not in marked:
			if cyclic(G, v, marked):
				return True
	return False


def path(G, u, v):
	'''
Return a path (a list of vertices) from vertex u to vertex v
if such a path exists, [] orherwise
	'''

	def find_path(G, v, target, predecessor):
		'''
	Find a path from vertice v to vertice target and
	set the predecessor map if such a path exists.
	predecessor[y] = x means we move from x to y on the path
		'''
		if v == target:
			return True
		for a in G.adjacents(v):
			if a not in predecessor:
				predecessor[a] = v
				if find_path(G, a, target, predecessor):
					return True
		return False

	def build_path(v, predecessor):
		'''
	Return the path as a list [v1, v2, ..., vN] from
	the predecessor map and the target vertex v
		'''
		thepath = [v]
		while v != predecessor[v]:
			v = predecessor[v]
			thepath.append(v)
		thepath.reverse()
		return thepath

	predecessor = {u: u}
	if find_path(G, u, v, predecessor):
		return build_path(v, predecessor)
	else:
		return []


def has_root(G):
	'''
Return a root of the directed graph G if such a root exists
None otherwise
	'''

	def dfs(G, v, marked):
		'''
	Perform a dsf from vertex v and mark all reachable vertices
	marked is the set of marked vertices
		'''
		marked.add(v)
		for a in G.adjacents(v):
			if a not in marked:
				dfs(G, a, marked)

	marked = set()
	for v in G.vertices():
		if v not in marked:
			top = v
			dfs(G, v, marked)

	marked.clear()
	dfs(G, top, marked)
	return top if len(marked) == G.vertex_count() else None


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
		('I', 'J')), True), "D5": graph_from_edgelist((
		('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('F', 'G'), ('G', 'H'), ('I', 'J'), ('J', 'A'),
		('K', 'E')), True)}


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
	thegraphs[n] = graph_from_edgelist(eval(e), d)


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
