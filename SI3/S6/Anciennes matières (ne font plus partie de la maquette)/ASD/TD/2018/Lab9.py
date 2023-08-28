# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Marc Gaetano

from heap import *

from graph import *


def prim(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Prim's algorithm.
	G is connected.
	Return the list of edges of that MST (in increasing order).
	"""
	pass


def kruskal(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Kruskal's algorithm.
	G is connected.
	Return the list of edges of that MST (in arbitrary order).
	"""
	pass


def primForest(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Prim's algorithm.
	G may not be connected.
	Return the list of edges of that MST (in increasing order).
	"""
	pass


def kruskalForest(G):
	"""
	Compute a Minimum Spanning Tree of weighted graph G using Kruskal's algorithm.
	G may not be connected.
	Return the list of edges of that MST (in arbitrary order).
	"""
	pass


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
		('A', 'D', 8), ('A', 'E', 9), ('A', 'F', 2), ('B', 'D', 1), ('B', 'E', 5), ('C', 'E', 6), ('C', 'F', 7),
		('D', 'E', 3), ('E', 'F', 4),
		('G', 'J', 8), ('G', 'K', 9), ('G', 'L', 2), ('H', 'J', 1), ('H', 'K', 5), ('I', 'K', 6), ('I', 'L', 7),
		('J', 'K', 3), ('K', 'L', 4)))}


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
	elif choice == "0":
		break
	elif choice == "1":
		build_graph()
	else:
		G = choose_graph()
		mst = menu[int(choice)][1](G)
		print(mst)
