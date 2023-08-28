from itertools import groupby
L = list(groupby(map(int, input().split())))
print(1+sum((L[i] > L[i-1]) == (L[i] > L[i+1]) for i in range(1, len(L)-1)))