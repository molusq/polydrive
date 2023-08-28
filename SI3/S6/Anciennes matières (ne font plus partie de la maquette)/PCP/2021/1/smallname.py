import sys
print(list(sorted(sys.stdin.readlines()[1:], key=lambda x: (len(x), x)))[0])
