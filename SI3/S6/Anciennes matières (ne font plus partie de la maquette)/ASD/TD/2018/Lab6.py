# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Marc Gaetano

import time

import Slowinteger


def selectionSort(L):
	'''
	Selection Sort
	'''
	for i in range(len(L) - 1):
		min = i
		for j in range(i + 1, len(L)):
			if L[j] < L[min]:
				min = j
		L[i], L[min] = L[min], L[i]


def insertionSort(L):
	'''
	Insertion Sort
	'''
	for i in range(1, len(L)):
		for j in range(i - 1, -1, -1):
			if L[j + 1] < L[j]:
				L[j + 1], L[j] = L[j], L[j + 1]
			else:
				break


def mergeSort(L):
	'''
	Mergesort
	'''
	if len(L) <= 1:
		return True

	def merge(fromList, toList, low, middle, high):
		i, j = low, middle
		for k in range(low, high):
			if j >= high or (i < middle and fromList[i] < fromList[j]):
				toList[k] = fromList[i]
				i += 1
			else:
				toList[k] = fromList[j]
				j += 1

	def transfer(fromList, toList, low, high):
		for i in range(low, high):
			fromList[i] = toList[i]

	def mergeAux(alist, helper, low, high):
		if high - low > 1:
			m = low + high // 2
			mergeAux(alist, helper, low, m)
			mergeAux(alist, helper, m, high)
			merge(alist, helper, low, m, high)
			transfer(helper, alist, low, high)

	mergeAux(L, list(L), 0, len(L))


# def quickSort(L):
# 	'''
# 	Quicksort
# 	'''
#
# 	def quickAux(alist, first, last):
# 		'''
# 		Recursively quicksort L[i:j]
# 		'''
# 		if first < last:
# 			pivot = partition(alist, first, last)
# 			quickAux(alist, first, pivot - 1)
# 			quickAux(alist, pivot + 1, last)
#
# 	def partition(alist, first, last):
# 		'''
# 		Do the partition of L[i:j+1] and return the index of pivot
# 		'''
# 		pivot = alist[first]
#
# 		left = first + 1
# 		right = last
#
# 		while 1:
# 			while left <= right and alist[left] <= pivot:
# 				left += 1
#
# 			while right >= left and alist[right] >= pivot:
# 				right -= 1
#
# 			if right < left:
# 				break
# 			else:
# 				alist[left], alist[right] = alist[right], alist[left]
#
# 		alist[first], alist[right] = alist[right], alist[first]
# 		return right
#
# 	quickAux(L, 0, len(L) - 1)
def quickSort(L, cutoff=10):
	'''
	6Quicksort
	'''

	def insertionSort(L, left, right):
		'''
	insertion sort of L[left:right+1]
		'''
		for i in range(left + 1, right):
			for j in range(i, left, -1):
				if L[j] < L[j - 1]:
					L[j], L[j - 1] = L[j - 1], L[j]
				else:
					break

	def partition(L, i, j):
		'''
	Do the partition of L[i:j+1] and return the index of pivot
		'''
		p = i
		for k in range(i + 1, j):
			if L[k] < L[i]:
				L[k], L[p + 1] = L[p + 1], L[k]
				p += 1
		L[i], L[p] = L[p], L[i]
		return p

	def quickAux(L, i, j):
		'''
		Recursively quicksort L[i:j]
		'''
		if j - i <= cutoff:
			insertionSort(L, i, j)
			return

		# Totalement optionnel :
		# a,b,c = i,j-1,(i+j)//2
		# a,b = (a,b) if L[a] < L[b] else (b,a)
		# p = a if L[c] < L[a] else b if L[c] > L[b] else c
		# L[p], L[i] = L[i], L[p]

		p = partition(L, i, j)
		quickAux(L, i, p)
		quickAux(L, p + 1, j)

	quickAux(L, 0, len(L))


def heapSort(L):
	'''
	Heapsort
	'''
	pass


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
