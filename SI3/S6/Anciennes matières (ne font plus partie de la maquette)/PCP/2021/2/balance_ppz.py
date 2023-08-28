def main():
  _, M = map(int, input().split())
  C = list(map(int, input().split()))
  V = list(map(int, input().split()))
  for i in range(M):
    V[C[2*i]] = abs(V[C[2*i]]-V[C[2*i+1]])
    V[C[2*i+1]] = 0
  S = sum(V)
  MAX = S//2+1
  MASK = 1 << MAX
  CACHE = 1
  for c in V:
    if c:
      CACHE |= ((CACHE<<c)%MASK)
  print(S-2*CACHE.bit_length()+2)

main()