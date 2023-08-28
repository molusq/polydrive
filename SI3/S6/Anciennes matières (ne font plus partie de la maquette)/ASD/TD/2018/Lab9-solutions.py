# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Marc Gaetano

from Lab5_correction import *

from graph import *
from partition import *


def prim(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Prim's algorithm.
	G is connected.
	Return the list of edges of that MST (in increasing order).
	"""

	mst = []  # the list of edges of the MST
	bh = BinaryHeap()  # the binary heap to collect the crossing edges
	v = G.random_vertex()  # a random vertex to start
	marked = {v}  # only the staring vertex is marked
	for e in G.incident_edges(v):  # all edges incident to the start vertex are crossing edges
		bh.add(e)  # so add them in the heap
	while len(marked) < G.vertex_count():  # if not all vertices are marked
		e = bh.pop()  # pop the minimum weighted edge from the heap
		a, b = e.endpoints()
		if a in marked and b in marked:  # if both end points are marked
			continue  # throw it away
		mst.append(e)  # else add it to the MST edge list
		x = a if b in marked else b
		marked.add(x)  # mark the unmarked endpoint vertex
		for e in G.incident_edges(x):  # for all incident edges to that vertex
			if e.opposite(x) not in marked:  # if the edge is crossing and the other
				bh.add(e)  # endpoint is not marked, add it to the heap

	return mst


def kruskal(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Kruskal's algorithm.
	G is connected.
	Return the list of edges of that MST (in arbitrary order).
	"""
	mst = []  # the list of edges of the MST
	bh = BinaryHeap()  # the binary heap to collect the edges
	forest = Partition()  # keeps track of forest clusters
	position = {}  # maps each node to its Partition entry

	for v in G.vertices():
		position[v] = forest.create_node(v)  # creates a node for each vertex

	for e in G.edges():  # for each edge
		bh.add(e)  # add it into the heap

	while len(forest) > 1:  # if there is more than one cluster in the forest
		edge = bh.pop()  # pop the minimum weighted edge from the heap
		u, v = edge.endpoints()
		a = forest.find(position[u])  # get the leaders of the endpoints
		b = forest.find(position[v])
		if a != b:  # if leaders are distinct
			mst.append(edge)  # add the edge to the mst
			forest.union(a, b)  # merge the two clusters

	return mst


def primForest(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Prim's algorithm.
	G may not be connected.
	Return the list of edges of that MST (in increasing order).
	"""
	mst = []  # the list of edges of the MST
	marked = set({})  # the marked vertices
	bh = BinaryHeap()  # the binary heap to collect the crossing edges
	for v in G.vertices():  # check each vertex v
		if v not in marked:  # process the connected component of v
			marked.add(v)  # only the staring vertex of the current connected component is marked
			for e in G.incident_edges(v):  # all edges incident to the start vertex are croissing edges
				bh.add(e)  # so add them in the heap
			while len(bh):  # if there are still vertices in the heap
				e = bh.pop()  # pop the minimum weighted edge from the heap
				a, b = e.endpoints()
				if a in marked and b in marked:  # if both end points are marked
					continue  # throw it away
				mst.append(e)  # else add it to the MST edge list
				x = a if b in marked else b
				marked.add(x)  # mark the unmarked endpoint vertex
				for e in G.incident_edges(x):  # for all incident edges to that vertex
					if e.opposite(x) not in marked:  # if the edge is crossing
						bh.add(e)  # add it to the heap

	return mst


def kruskalForest(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Kruskal's algorithm.
	G may not be connected.
	Return the list of edges of that MST (in arbitrary order).
	"""
	mst = []  # the list of edges of the MST
	bh = BinaryHeap()  # the binary heap to collect the edges
	forest = Partition()  # keeps track of forest clusters
	position = {}  # maps each node to its Partition entry

	for v in G.vertices():
		position[v] = forest.create_node(v)  # creates a node for each vertex

	for e in G.edges():  # for each edge
		bh.add(e)  # add it into the heap

	while len(forest) > 1:  # if there is more than one cluster in the forest
		edge = bh.pop()  # pop the minimum weighted edge from the heap
		u, v = edge.endpoints()
		a = forest.find(position[u])  # get the leaders of the endpoints
		b = forest.find(position[v])
		if a != b:  # if leaders are distinct
			mst.append(edge)  # add the edge to the mst
			forest.union(a, b)  # merge the two clusters

	return mst


###
### TEST CODE
###

thegraphs = {"C1": graph_from_edgelist((
	('A', 'C', 1), ('A', 'D', 5), ('A', 'E', 2), ('B', 'D', 3), ('C', 'E', 6), ('D', 'E', 4))),
	"C2": graph_from_edgelist((
		('A', 'D', 8), ('A', 'E', 9), ('A', 'F', 2), ('B', 'D', 1), ('B', 'E', 5), ('C', 'E', 6), ('C', 'F', 7),
		('D', 'E', 3), ('E', 'F', 4))), "C3": graph_from_edgelist((
		('A', 'D', 9), ('A', 'F', 2), ('A', 'G', 8), ('B', 'D', 4), ('B', 'E', 11), ('B', 'G', 10), ('C', 'E', 6),
		('C', 'F', 7), ('C', 'G', 12), ('D', 'G', 3), ('E', 'G', 5), ('F', 'G', 1))), "C4": graph_from_edgelist((
	)), "NC1": graph_from_edgelist((
		('A', 'C', 1), ('A', 'E', 2), ('B', 'D', 4), ('C', 'E', 3))), "NC2": graph_from_edgelist((
		('A', 'D', 8), ('A', 'E', 9), ('B', 'D', 1), ('B', 'E', 5), ('D', 'E', 3),
		('C', 'F', 7), ('C', 'H', 6), ('F', 'G', 2), ('F', 'H', 4), ('G', 'H', 5)))}


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
	e = input("Enter the list of weighted edges: ")
	thegraphs[n] = graph_from_edgelist(e, True)


menu = (("exit", None), ("build graph", None), ("prim", prim), ("kruskal", kruskal), ("primForest", primForest),
		("kruskalForest", kruskalForest))
i, prompt = 0, ""
for entry in menu:
	prompt += "  " + entry[0] + "(" + str(i) + ")"
	i += 1
prompt += ": "

while True:
	choice = int(input(prompt))
	if choice < 0 or choice >= len(menu):
		print("wrong choice")
	elif choice == 0:
		break
	elif choice == 1:
		build_graph()
	else:
		G = choose_graph()
		mst = menu[int(choice)][1](G)
		print(mst)
