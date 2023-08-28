# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Marc Gaetano

from BinaryHeapSpecial import *


def dijkstra(G, source):
	'''
	Computes all shortest paths from source to all other vertices.
	Returns a dictionary 'path' such that if path[v] = u, u is the
	predecessor of v on the shortest path form source to v
	'''
	known = set()
	path = {}
	bhs = BinaryHeapSpecial()
	for v in G.vertices():
		v.setValue(float('inf'))
		bhs.add(v)
	source.setValue(0)
	bhs.decreaseKey(source)
	while len(bhs) > 0:
		v = bhs.pop()
		known.add(v)
		for e in G.incident_edges(v):
			a = e.opposite(v)
			if a not in known:
				if v.value() + e.value() < a.value():
					a.setValue(v.value() + e.value())
					bhs.decreaseKey(a)
					path[a] = v
	return path


def print_paths(path, source):
	'''
	Given a dictionary 'path' and a 'source', prints out
	all shortest paths from source to all other reachable
	vertices (i.e. the ones appearing in 'path')
	'''

	def aux(path, source, v):
		if v is source:
			print(v, end=" ")
		else:
			aux(path, source, path[v])
			print(v, end=" ")

	for v in path:
		aux(path, source, v)
		print()


###
### TEST CODE
###

thegraphs = {}
thegraphs["G1"] = graph_from_edgelist((
	('A', 'B', 2), ('A', 'C', 1), ('A', 'D', 4), ('B', 'C', 5), ('B', 'F', 2), ('C', 'A', 9), ('C', 'E', 11),
	('D', 'C', 2), ('D', 'E', 8),
	('E', 'B', 10), ('E', 'G', 1), ('F', 'H', 3), ('G', 'E', 3), ('G', 'F', 2), ('H', 'G', 1)), True)
thegraphs["G2"] = graph_from_edgelist((
	('A', 'C', 2), ('A', 'D', 1), ('B', 'A', 2), ('C', 'D', 1), ('C', 'F', 2), ('D', 'E', 1), ('D', 'F', 6),
	('D', 'G', 5), ('E', 'B', 1), ('F', 'G', 10), ('G', 'E', 3)), True)


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


def choose_source(G):
	while True:
		tag = input("your source: ").strip()
		v = G.get_vertex(tag)
		if v is not None:
			return v


def print_path_length(G):
	for v in G.vertices():
		print(str(v) + ":" + str(v.value()), end="  ")
	print()


def build_graph():
	display_available(thegraphs)
	n = input("Enter the name of your graph: ").strip()
	e = input("Enter the list of weighted edges: ")
	thegraphs[n] = graph_from_edgelist(e, True)


menu = (("exit", None), ("build graph", None), ("dijkstra", dijkstra))
i, prompt = 0, ""
for entry in menu:
	prompt += entry[0] + "(" + str(i) + ")" + " "
	i += 1
prompt += ": "

print("Welcome to the Dijkstra tester\n")

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
		source = choose_source(G)
		sp = menu[int(choice)][1](G, source)
		print()
		print_path_length(G)
		print()
		print_paths(sp, source)
		print()
