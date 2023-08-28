import sys
input()
coeffs = list(map(int, input().split(" ")))
etu = [x.strip().split(" ") for x in sys.stdin.readlines()]
tuples = []
for e in etu:
	tuples.append((e[0], list(map(int,e[1:]))))
def wa(vals, weights):
	return sum(a*b for a,b in zip(vals, weights)) / sum(weights)
items = [(x[0], wa(x[1], weights=coeffs)) for x in tuples]
items.sort(key=lambda w: w[1])
if sum(1 for x in items if x[1] == items[-1][1]) > 1:
	print("EX AEQUO")
else:
	print(items[-1][0])