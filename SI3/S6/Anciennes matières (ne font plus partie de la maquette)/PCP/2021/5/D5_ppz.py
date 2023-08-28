rom functools import cmp_to_key
from math import hypot


def main():
  N = int(input())
  L, pmin = [], tuple(map(int, input().split()))
  for _ in range(N-1):
    p = tuple(map(int, input().split()))
    if p < pmin:
      L.append(pmin)
      pmin = p
    else:
      L.append(p)

  def cmp(p1, p2): 
    d=(p1[0]-pmin[0])*(p2[1]-pmin[1])-(p1[1]-pmin[1])*(p2[0]-pmin[0])
    if d != 0 : return d
    d = p1[0]-p2[0]
    if d != 0 : return d
    return p1[1]-p2[1]
  L.sort(key=cmp_to_key(cmp))
  #print(L)
  L.append(pmin)
  E = [pmin]
  for x, y in L:
    while len(E) > 1:
      d = (x-E[-1][0])*(E[-1][1]-E[-2][1])-(y-E[-1][1])*(E[-1][0]-E[-2][0])
      #print(d)
      if d < 0 or (d == 0 and (x-E[-1][0])*(E[-1][0]-E[-2][0])+(y-E[-1][1])*(E[-1][1]-E[-2][1]) > 0):
        E.pop()
      else:
        break
    E.append((x, y))
  #print(E)
  D = sum(hypot(E[i+1][0]-E[i][0], E[i+1][1]-E[i][1]) for i in range(len(E)-1))
  print(round(D))


main()