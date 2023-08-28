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

def topo_1(G):
	"""
	Return a list of vertices of directed acyclic graph g in topological order.
	This function should be based on the algorithm studied in class
	"""
	topo = []  # a list of vertices placed in topological order
	stack = []

	marked = {}
	nb = 0
	for v in G.vertices():
		# if v not in marked:
		# 	marked[v] = 0
		# 	nb += 1
		# for a in G.adjacents():
		# 	if a not in marked:
		# 		nb += 1
		# 	else:
		# 		marked[a] += 1
		marked[v] = G.degree(v, False)
		if marked[v] == 0:
			stack.append(v)
	while stack:
		i = stack.pop()
		for a in G.adjacents(i):
			marked[a] -= 1
			if marked[a] == 0:
				stack.append(a)
		topo.append(i)
	return topo


def topo_2(G):
	"""
	Return a list of vertices of directed acyclic graph G in topological order.
	This function should be based on DFS algorithm
	"""

	def dfs(G, v, marked, topo):
		"""
		Perform a depth-first search from
		vertex v a nd add all the discovered
		vertices inside the list 'topo' so
		they appear in topological order
		"""
		marked.add(v)
		stack = [(v, list(G.adjacents(v)))]
		while stack:
			node, padj = stack[-1]
			if padj:
				a= adj.pop()
				if a not in marked:
					marked.add(a)
					stack.append((a, list(G.adjacents(a))))
				else:
					topo.append()
					stack.pop()
	marked = set()
	topo = []
	for n in G.vertices():
		if n not in marked:
			dfs(G, n, marked, topo)

	return list(reversed(topo))


def topo_unique(G):
	"""
	Return True if G have a unique topological sort,
	False otherwise
	"""

	return True


#
# TEST CODE
#

thegraphs = {"D1": graph_from_edgelist((
	('A', 'D'), ('C', 'A'), ('C', 'E'), ('D', 'B'), ('E', 'A'), ('E', 'D')), True), "D2": graph_from_edgelist((
	('A', 'D'), ('A', 'F'), ('C', 'A'), ('C', 'E'), ('D', 'B'), ('E', 'D'), ('F', 'B')), True),
	"D3": graph_from_edgelist((
		('A', 'C'), ('A', 'E'), ('B', 'D'), ('B', 'F'), ('D', 'F'), ('D', 'G'), ('E', 'C')), True),
	"D4": graph_from_edgelist((
		('A', 'C'), ('A', 'E'), ('B', 'D'), ('C', 'E'), ('C', 'G'), ('D', 'A'), ('F', 'B'), ('F', 'D')), True),
	"D5": graph_from_edgelist((
		('B', 'E'), ('B', 'C'), ('B', 'D'), ('C', 'D'), ('C', 'F'), ('D', 'E'), ('A', 'B'), ('A', 'C'), ('A', 'D'),
		('D', 'F'), ('D', 'G'), ('E', 'H'), ('E', 'G'), ('F', 'E'), ('F', 'G'), ('F', 'I'), ('G', 'H'), ('G', 'I'),
		('G', 'J'), ('H', 'I'), ('H', 'J'), ('I', 'J')), True)}


def display_available(thegraphs):
	l = [g for g in thegraphs]
	print("Available graphs are:", sorted(l))
	return l


def choose_graph():
	l = display_available(thegraphs)
	while True:
		g = input("your choice: ").strip()
		if g in l:
			return thegraphs[g]


def build_graph():
	display_available(thegraphs)
	n = input("Enter the name of your graph: ").strip()
	e = input("Enter the list of edges: ")
	thegraphs[n] = graph_from_edgelist(e, True)


while True:
	choice = input("exit(0)  topo_1(1)  topo_2(2)  topo_unique(3)  new graph(4): ")
	if choice == "0":
		break
	elif choice == "4":
		build_graph()
	elif choice == "1":
		G = choose_graph()
		t = topo_1(G)
		print(t)
	elif choice == "2":
		G = choose_graph()
		t = topo_2(G)
		print(t)
	elif choice == "3":
		G = choose_graph()
		if topo_unique(G):
			print("Topological order is unique")
		else:
			print("Topological order is NOT unique")
	else:
		print("wrong choice")
