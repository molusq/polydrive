from collections import Counter
print(sum(c//2 for _,c in Counter(input() for _ in range(int(input()))).items()))