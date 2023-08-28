# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Marc Gaetano

import random
import time

import Slowinteger
import binaryheap


def selectionSort(L):
	'''
Selection Sort
	'''
	for i in range(0, len(L) - 1):
		min = i
		for j in range(i, len(L)):
			if L[j] < L[min]:
				min = j
		L[i], L[min] = L[min], L[i]


def insertionSort(L):
	'''
Insertion Sort
	'''
	for i in range(1, len(L)):
		j = i
		while j > 0 and L[j] < L[j - 1]:
			L[j], L[j - 1] = L[j - 1], L[j]
			j -= 1


def quickSort(L, cutoff=10):
	'''
Quicksort
	'''

	def insertionSort(L, left, right):
		'''
	insertion sort of L[left:right+1]
		'''
		for i in range(left, right + 1):
			j = i
			while j > 0 and L[j] < L[j - 1]:
				L[j], L[j - 1] = L[j - 1], L[j]
				j -= 1

	def partition(L, i, j):
		'''
	Do the partition of L[i:j+1] and return the index of pivot
		'''
		k = random.randint(i, j)
		L[i], L[k] = L[k], L[i]
		p = i
		for k in range(i + 1, j + 1):
			if L[k] < L[i]:
				p += 1
				L[p], L[k] = L[k], L[p]
		L[p], L[i] = L[i], L[p]
		return p

	def quickAux(L, i, j):
		'''
	Recursively quicksort L[i:j+1]
		'''
		if j - i < cutoff:
			insertionSort(L, i, j)
		else:
			p = partition(L, i, j)
			quickAux(L, i, p - 1)
			quickAux(L, p + 1, j)

	quickAux(L, 0, len(L) - 1)


def mergeSort(L):
	'''
Mergesort
	'''

	def merge(from_list, to_list, low, middle, high):
		l, r, m = low, middle + 1, low
		while l <= middle and r <= high:
			if from_list[l] < from_list[r]:
				to_list[m] = from_list[l]
				l += 1
			else:
				to_list[m] = from_list[r]
				r += 1
			m += 1
		while l <= middle:
			to_list[m] = from_list[l]
			l += 1
			m += 1
		while r <= high:
			to_list[m] = from_list[r]
			r += 1
			m += 1

	def transfer(from_list, to_list, low, high):
		for i in range(low, high + 1):
			to_list[i] = from_list[i]

	def mergeAux(alist, helper, low, high):
		if low < high:
			middle = (low + high) // 2
			mergeAux(alist, helper, low, middle)
			mergeAux(alist, helper, middle + 1, high)
			merge(alist, helper, low, middle, high)
			transfer(helper, alist, low, high)

	helper = [None] * len(L)
	mergeAux(L, helper, 0, len(L) - 1)


def heapSort(L):
	'''
Heapsort
	'''
	bhc = binaryheap.BinaryHeapContainer(L)
	for i in range(-1, -len(L), -1):
		L[i] = bhc.pop()


###
### DO NOT CHANGE THE CODE BELOW
###

def isSorted(L):
	for i in range(1, len(L)):
		if L[i - 1] > L[i]:
			return False
	return True


fun = [selectionSort, insertionSort, heapSort, quickSort, mergeSort]
l = []
while True:
	choice = input(
		"exit(0) generate a new list(1) selection sort(2) insertion sort(3) heapsort(4) quicksort(5) mergesort(6): ")
	if choice == "0":
		break
	if choice == "1":
		m = int(input("\nenter maximum value in the list: ").strip())
		n = int(input("enter the number of values in the list: ").strip())
		l.clear()
		for i in range(n + 1):
			l.append(Slowinteger.slowinteger(1, m))
		print(l)
		print()
	else:
		copy = l[:]
		f = fun[int(choice) - 2]
		print(f.__doc__)
		print("random:", copy)
		start = time.time()
		f(copy)
		stop = time.time()
		print("sorted:", copy)
		print("result is correct!" if isSorted(copy) else "result is NOT correct!")
		print("computation time: {:1.3f}s\n".format(stop - start))
