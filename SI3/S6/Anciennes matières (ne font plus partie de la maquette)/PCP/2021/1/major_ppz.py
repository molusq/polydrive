#Exercice 1.Major
def main():
  N, M = map(int, input().split())
  C = list(map(int, input().split()))
  best, bestv = "", -1
  for _ in range(N):
    T = input().split()
    res = sum(int(T[i+1])*C[i] for i in range(M))
    if res > bestv:
      best, bestv = T[0], res
    elif res == bestv:
      best = 'EX AEQUO'
  print(best)


main()
